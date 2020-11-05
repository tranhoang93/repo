package com.thanh.boot.security;

import org.springframework.security.core.AuthenticationException;

public class InvalidScopeException extends AuthenticationException {
    public InvalidScopeException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidScopeException(String msg) {
        super(msg);
    }
}
