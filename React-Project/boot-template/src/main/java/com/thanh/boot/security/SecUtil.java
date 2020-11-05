package com.thanh.boot.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.thanh.boot.util.Constants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

public class SecUtil {
    public static synchronized void writeCorsHeaders(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods",
                "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, " +
                        "OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
    }

    public static String createJwtToken(UsernamePasswordAuthenticationToken token, String issuer, long expSeconds,
                                        Algorithm algorithm, String scope) {
        Long userId = (Long) token.getDetails();
        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + (expSeconds * 1000)))
                .withJWTId(UUID.randomUUID().toString())
                .withSubject(token.getPrincipal().toString())
                .withClaim(Constants.CLAIM_USERNAME, token.getPrincipal().toString())
                .withClaim(Constants.CLAIM_USER_ID, userId)
                .withClaim(Constants.CLAIM_SCOPE, scope)
                .sign(algorithm);
    }
}
