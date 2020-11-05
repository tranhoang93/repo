package com.thanh.boot.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thanh.boot.util.Utils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnauthorizedAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.reset();
        response.setStatus(401);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("code", "UNAUTHORIZED");
        node.put("description", "No access token found in the request. " +
                "An access token must be set in 'Authorization' header or in 'accessToken' parameter.");
        SecUtil.writeCorsHeaders(request, response);
        Utils.writeObjectNodeToResponse(node, response);
    }
}
