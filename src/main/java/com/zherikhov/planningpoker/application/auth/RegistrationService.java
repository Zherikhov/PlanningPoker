package com.zherikhov.planningpoker.application.auth;

public interface RegistrationService {
    UserResponse register(RegisterRequest req);
}
