package com.zherikhov.planningpoker.application.auth;

import com.zherikhov.planningpoker.domain.user.User;

/**
 * Service for registering new users.
 */
public interface RegistrationService {
    /**
     * Registers a new user based on the provided command.
     *
     * @param req registration data
     * @return created user domain model
     */
    User register(RegisterUser req);
}
