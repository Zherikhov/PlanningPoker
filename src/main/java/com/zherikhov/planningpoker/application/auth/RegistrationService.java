package com.zherikhov.planningpoker.application.auth;

import com.zherikhov.planningpoker.api.auth.RegisterRequest;
import com.zherikhov.planningpoker.api.auth.UserResponse;

public interface RegistrationService {
    UserResponse register(RegisterRequest req);
}
