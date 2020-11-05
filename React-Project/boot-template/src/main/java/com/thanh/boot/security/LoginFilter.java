package com.thanh.boot.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thanh.boot.config.ApplicationProperties;
import com.thanh.boot.entity.LoginSession;
import com.thanh.boot.entity.User;
import com.thanh.boot.mapper.UserMapper;
import com.thanh.boot.service.LoginSessionService;
import com.thanh.boot.util.Constants;
import com.thanh.boot.util.Utils;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    private MessageSource messageSource;
    private KeyPairProvider keyPairProvider;
    private LoginSessionService loginSessionService;
    private ApplicationProperties applicationProperties;

    protected LoginFilter() {
        super(Constants.LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        logger.debug("Attempting authentication for a request that matches {}", Constants.LOGIN_URL);
        LoginRequest loginRequest = getLoginRequest(request);
        if (loginRequest != null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            return getAuthenticationManager().authenticate(token);
        } else {
            throw new BadCredentialsException("Login request is null");
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

            // User ID was set in JpaAuthenticationProvider
            Long userId = (Long) token.getDetails();

            String accessTokenId = UUID.randomUUID().toString();
            String refreshTokenId = UUID.randomUUID().toString();
            long accessTokenLifeTime = applicationProperties.getAccessTokenLifeTimeMinutes() * 60_000;
            long refreshTokenLifeTime = applicationProperties.getRefreshTokenLifeTimeMinutes() * 60_000;
            String accessToken = JWT.create()
                    .withIssuer(request.getRequestURL().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenLifeTime))
                    .withJWTId(accessTokenId)
                    .withSubject(token.getPrincipal().toString())
                    .withClaim(Constants.CLAIM_USERNAME, token.getPrincipal().toString())
                    .withClaim(Constants.CLAIM_USER_ID, userId)
                    .withClaim(Constants.CLAIM_SCOPE, Constants.CLAIM_SCOPE_ACCESS)
                    .sign(algorithm);

            String refreshToken = JWT.create()
                    .withIssuer(request.getRequestURL().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenLifeTime))
                    .withJWTId(refreshTokenId)
                    .withSubject(token.getPrincipal().toString())
                    .withClaim(Constants.CLAIM_USERNAME, token.getPrincipal().toString())
                    .withClaim(Constants.CLAIM_USER_ID, userId)
                    .withClaim(Constants.CLAIM_SCOPE, Constants.CLAIM_SCOPE_REFRESH_TOKEN)
                    .sign(algorithm);


            LoginSuccessResponse loginSuccessResponse = new LoginSuccessResponse();
            loginSuccessResponse.setAccessToken(accessToken);
            loginSuccessResponse.setRefreshToken(refreshToken);
            loginSuccessResponse.setExpiresIn(300);
            loginSuccessResponse.setRefreshTokenExpiresIn(18000);
            loginSuccessResponse.setTokenType("Bearer");
            loginSuccessResponse.setScope(Constants.CLAIM_SCOPE_ACCESS);

            String str = mapper.writeValueAsString(loginSuccessResponse);

            storeLoginSessionInfo(request, accessTokenId, refreshTokenId, userId);

            response.setStatus(200);
            SecUtil.writeCorsHeaders(request, response);
            Utils.writeToResponse(str, response);
        } else {
            logger.warn("authResult is not of type {}", UsernamePasswordAuthenticationToken.class.getName());
        }
    }

    private void storeLoginSessionInfo(HttpServletRequest request, String accessTokenId, String refreshTokenId, long userId) {
        LoginSession loginSession = new LoginSession();
        String ipAddress = Utils.getRealIp(request);
        loginSession.setIpAddress(ipAddress);
        loginSession.setAccessTokenId(accessTokenId);
        loginSession.setRefreshTokenId(refreshTokenId);
        String userAgent = request.getHeader("User-Agent");
        loginSession.setUserAgent(userAgent);
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        User user = userMapper.fromId(userId);
        loginSession.setUser(user);
        loginSessionService.save(loginSession);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        Locale locale = LocaleContextHolder.getLocale();
        if (failed instanceof UsernameNotFoundException || failed instanceof BadCredentialsException) {
            response.setStatus(401);
            node.put(Constants.CODE, Constants.CODE_UNAUTHORIZED);
            String description = messageSource.getMessage("badCredentials", null, locale);
            node.put(Constants.DESCRIPTION, description);
        } else if (failed instanceof DisabledException){
            response.setStatus(401);
            node.put(Constants.CODE, Constants.CODE_UNAUTHORIZED);
            String description = messageSource.getMessage("accountDisabled", null, locale);
            node.put(Constants.DESCRIPTION, description);
        }

        else {
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

    public void setLoginSessionService(LoginSessionService loginSessionService) {
        this.loginSessionService = loginSessionService;
    }

    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }
}
