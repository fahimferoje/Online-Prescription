package com.cmed.prescription.config;

import org.springframework.security.core.AuthenticationException;

public class PasswordNotMatchException extends AuthenticationException {
    public PasswordNotMatchException(String msg) {
        super(msg);
    }
}
