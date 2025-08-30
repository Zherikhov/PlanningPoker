package com.zherikhov.planningpoker.infrastructure.security;

import com.zherikhov.planningpoker.application.auth.LoginRequest;
import com.zherikhov.planningpoker.application.auth.LoginResponse;
import com.zherikhov.planningpoker.application.auth.UserResponse;
import com.zherikhov.planningpoker.application.auth.AuthService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtProvider jwtProvider;

    private static final String DEMO_EMAIL = "user@example.com";
    private static final String DEMO_PASSWORD = "secret";
    private static final UserResponse DEMO_USER = new UserResponse(
            UUID.fromString("00000000-0000-0000-0000-000000000001"), DEMO_EMAIL, "Demo User");

    public AuthServiceImpl(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Optional<LoginResponse> login(LoginRequest request) {
        if (DEMO_EMAIL.equals(request.email()) && DEMO_PASSWORD.equals(request.password())) {
            String token = jwtProvider.generateToken(DEMO_USER.id().toString());
            return Optional.of(new LoginResponse(token, 3600, DEMO_USER));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, Object>> refresh(String refreshToken) {
        if (refreshToken == null) {
            return Optional.empty();
        }
        String token = jwtProvider.generateToken(DEMO_USER.id().toString());
        return Optional.of(Map.of("accessToken", token, "expiresIn", 3600));
    }

    @Override
    public Optional<UserResponse> me(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Optional.empty();
        }
        String token = authHeader.substring(7);
        try {
            jwtProvider.getSubject(token);
            return Optional.of(DEMO_USER);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
