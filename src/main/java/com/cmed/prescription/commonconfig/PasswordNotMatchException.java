package com.cmed.prescription.commonconfig;

import org.springframework.security.core.AuthenticationException;

public class PasswordNotMatchException extends AuthenticationException {
    public PasswordNotMatchException(String msg) {
        super(msg);
    }
}
