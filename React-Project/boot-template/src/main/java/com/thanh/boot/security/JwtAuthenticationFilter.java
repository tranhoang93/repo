package com.thanh.boot.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thanh.boot.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private MessageSource messageSource;
    private KeyPairProvider keyPairProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = getAccessToken(request);

        UsernamePasswordAuthenticationToken authentication = null;
        if (accessToken != null) {
            DecodedJWT jwt = JWT.decode(accessToken);
            Algorithm algorithm = Algorithm.RSA256(keyPairProvider.getRsaPublicKey(), null);
            algorithm.verify(jwt);

            Date exp = jwt.getExpiresAt();
            if (exp.before(new Date())) {
                logger.debug("Expired access token: {}", jwt.getId());
                writeExpiredResponse(request, response);
            } else {

                String username = jwt.getClaim("username").asString();
                long userId = jwt.getClaim("userId").asLong();

                List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));

                authentication = new UsernamePasswordAuthenticationToken(username, "[Protected]", authorities);
                authentication.setDetails(userId);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }

        } else {
            filterChain.doFilter(request, response);
        }


    }

    private void writeExpiredResponse(HttpServletRequest request, HttpServletResponse response) {
        response.reset();
        response.setStatus(401);
        response.setHeader("Content-Type", "application/json; charset=utf-8");
        SecUtil.writeCorsHeaders(request, response);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("code", "ACCESS_TOKEN_EXPIRED");
        String message = messageSource.getMessage("tokenExpired", null, LocaleContextHolder.getLocale());

        node.put("message", message);
        try {
            String str = mapper.writeValueAsString(node);
            Utils.writeToResponse(str, response);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    private String getAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        if (accessToken == null) {
            accessToken = request.getParameter("accessToken");
        }
        if (accessToken != null) {
            if (accessToken.startsWith("Bearer ")) {
                accessToken = accessToken.substring(7);
            }
        }
        return accessToken;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setKeyPairProvider(KeyPairProvider keyPairProvider) {
        this.keyPairProvider = keyPairProvider;
    }
}
