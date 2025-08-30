package com.zherikhov.planningpoker.application.auth;

import com.zherikhov.planningpoker.domain.common.DomainException;

/**
 * Thrown when attempting to register with an email that already exists.
 */
public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException(String email) {
        super("Email already registered: " + email);
    }
}
