package com.zherikhov.planningpoker.auth.api;

import com.zherikhov.planningpoker.auth.api.RegisterRequest;
import com.zherikhov.planningpoker.auth.api.UserResponse;

public interface RegistrationService {
    UserResponse register(RegisterRequest req);
}
