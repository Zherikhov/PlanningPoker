package com.zherikhov.planningpoker.application.auth;

import com.zherikhov.planningpoker.api.auth.LoginRequest;
import com.zherikhov.planningpoker.api.auth.LoginResponse;
import com.zherikhov.planningpoker.api.auth.UserResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.Optional;

public interface AuthService {
    Optional<LoginResponse> login(LoginRequest request, HttpServletResponse response);
    Optional<Map<String, Object>> refresh(String refreshToken);
    Optional<UserResponse> me(String authHeader);
}
