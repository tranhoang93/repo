package com.thanh.boot.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thanh.boot.util.Constants;
import com.thanh.boot.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RefreshTokenFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger logger = LoggerFactory.getLogger(RefreshTokenFilter.class);
    private MessageSource messageSource;
    private KeyPairProvider keyPairProvider;

    protected RefreshTokenFilter() {
        super(Constants.REFRESH_TOKEN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        logger.debug("Handling refresh token request that matches: {}", Constants.REFRESH_TOKEN_URL);
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || authorizationHeader.trim().length() == 0) {
            logger.debug("No Authorization header found. Now throw authentication exception");
            throw new AuthenticationCredentialsNotFoundException("No Authorization header found in refresh token request");
        } else {
            String refreshToken = null;
            if (authorizationHeader.startsWith("Bearer ")) {
                refreshToken = authorizationHeader.substring(7);
            } else {
                refreshToken = authorizationHeader;
            }
            DecodedJWT jwt = JWT.decode(refreshToken);
            RSAPublicKey publicKey = keyPairProvider.getRsaPublicKey();
            RSAPrivateKey privateKey = keyPairProvider.getRsaPrivateKey();
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

            try {
                algorithm.verify(jwt);
            } catch (SignatureVerificationException e) {
                logger.error("", e);
                throw new BadCredentialsException("Invalid JWT signature");
            }

            String scope = null;
            Claim scopeClaim = jwt.getClaim(Constants.CLAIM_SCOPE);
            if (scopeClaim != null) {
                scope = scopeClaim.asString();
            }

            if (!Constants.CLAIM_SCOPE_REFRESH_TOKEN.equals(scope)) {
                logger.debug("Invalid scope: Scope is not {}", Constants.CLAIM_SCOPE_REFRESH_TOKEN);
                throw new InvalidScopeException("Invalid scope: " + scope);
            }


            Date exp = jwt.getExpiresAt();
            if (exp.before(new Date())) {
                throw new CredentialsExpiredException("Refresh token has expired");
            }


            logger.debug("Now return ass-min");
            String username = jwt.getClaim(Constants.CLAIM_USERNAME).asString();
            Long userId = jwt.getClaim(Constants.CLAIM_USER_ID).asLong();
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(username, "[Private]", authorities);
            authenticationToken.setDetails(userId);
            return authenticationToken;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        if (authResult instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authResult;
            ObjectMapper mapper = new ObjectMapper();

            RSAPublicKey publicKey = keyPairProvider.getRsaPublicKey();
            RSAPrivateKey privateKey = keyPairProvider.getRsaPrivateKey();
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);


            String accessToken
                    = SecUtil.createJwtToken(token, "fresh-token-service",
                    10, algorithm, Constants.CLAIM_SCOPE_ACCESS);
            String refreshToken
                    = SecUtil.createJwtToken(token, "fresh-token-service",
                    120, algorithm, Constants.CLAIM_SCOPE_REFRESH_TOKEN);

            LoginSuccessResponse loginSuccessResponse = new LoginSuccessResponse();
            loginSuccessResponse.setAccessToken(accessToken);
            loginSuccessResponse.setRefreshToken(refreshToken);
            loginSuccessResponse.setExpiresIn(300);
            loginSuccessResponse.setRefreshTokenExpiresIn(18000);
            loginSuccessResponse.setTokenType("Bearer");
            loginSuccessResponse.setScope(Constants.CLAIM_SCOPE_REFRESH_TOKEN);

            String str = mapper.writeValueAsString(loginSuccessResponse);

            response.setStatus(200);
            SecUtil.writeCorsHeaders(request, response);
            Utils.writeToResponse(str, response);
        } else {
            logger.warn("authResult is not of type {}", UsernamePasswordAuthenticationToken.class.getName());
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        if (failed instanceof AuthenticationCredentialsNotFoundException) {
            response.setStatus(401);
            node.put(Constants.CODE, Constants.CODE_UNAUTHORIZED);
            node.put(Constants.DESCRIPTION, "No Authorization header found");
        } else if (failed instanceof BadCredentialsException) {
            response.setStatus(401);
            node.put(Constants.CODE, Constants.CODE_UNAUTHORIZED);
            node.put(Constants.DESCRIPTION, "Invalid refresh token");
        } else if (failed instanceof InvalidScopeException) {
            response.setStatus(401);
            node.put(Constants.CODE, Constants.CODE_UNAUTHORIZED);
            node.put(Constants.DESCRIPTION, "Invalid scope exception. You may be using the wrong token.");
        } else if (failed instanceof CredentialsExpiredException) {
            response.setStatus(401);
            node.put(Constants.CODE, Constants.CODE_REFRESH_TOKEN_EXPIRED);
            node.put(Constants.DESCRIPTION, "Refresh token has expired");
        } else {
            // Default
            logger.error("Could not refresh token due to a server error", failed);
            response.setStatus(500);
            node.put(Constants.CODE, "INTERNAL_SERVER_ERROR");
            String msg = messageSource.getMessage("internalServerError", null, LocaleContextHolder.getLocale());
            node.put(Constants.DESCRIPTION, msg);
        }

        SecUtil.writeCorsHeaders(request, response);
        String str = mapper.writeValueAsString(node);
        Utils.writeToResponse(str, response);
    }

    private LoginRequest getLoginRequest(HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(request.getInputStream(), LoginRequest.class);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setKeyPairProvider(KeyPairProvider keyPairProvider) {
        this.keyPairProvider = keyPairProvider;
    }
}
