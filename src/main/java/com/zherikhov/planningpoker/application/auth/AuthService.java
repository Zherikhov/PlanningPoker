package com.zherikhov.planningpoker.application.auth;

import java.util.Map;
import java.util.Optional;

public interface AuthService {
    Optional<LoginResponse> login(LoginRequest request);
    Optional<Map<String, Object>> refresh(String refreshToken);
    Optional<UserResponse> me(String authHeader);
}
