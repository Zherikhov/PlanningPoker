package com.zherikhov.planningpoker.domain.user;

import com.zherikhov.planningpoker.domain.common.DomainException;

/**
 * Thrown when attempting to register an email that already exists.
 */
public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException(String email) {
        super("Email already registered: " + email);
    }
}
