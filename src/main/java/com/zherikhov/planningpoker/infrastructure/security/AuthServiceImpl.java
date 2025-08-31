package com.zherikhov.planningpoker.infrastructure.security;

import com.zherikhov.planningpoker.application.auth.AuthService;
import com.zherikhov.planningpoker.application.auth.LoginRequest;
import com.zherikhov.planningpoker.application.auth.LoginResponse;
import com.zherikhov.planningpoker.application.auth.UserResponse;
import com.zherikhov.planningpoker.infrastructure.persistence.dao.AppUserJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.AppUserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtProvider jwtProvider;
    private final AppUserJpaRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(JwtProvider jwtProvider, AppUserJpaRepository repository) {
        this.jwtProvider = jwtProvider;
        this.repository = repository;
    }

    @Override
    public Optional<LoginResponse> login(LoginRequest request) {
        String email = request.email().trim().toLowerCase();
        Optional<AppUserEntity> userOpt = repository.findByEmailIgnoreCase(email);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }
        AppUserEntity user = userOpt.get();
        if (!encoder.matches(request.password(), user.getPasswordHash())) {
            return Optional.empty();
        }
        String token = jwtProvider.generateToken(user.getId());
        UserResponse u = new UserResponse(UUID.fromString(user.getId()), user.getEmail(), user.getDisplayName());
        return Optional.of(new LoginResponse(token, 3600, u));
    }

    @Override
    public Optional<Map<String, Object>> refresh(String refreshToken) {
        if (refreshToken == null) {
            return Optional.empty();
        }
        try {
            String userId = jwtProvider.getSubject(refreshToken);
            String token = jwtProvider.generateToken(userId);
            return Optional.of(Map.of("accessToken", token, "expiresIn", 3600));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserResponse> me(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Optional.empty();
        }
        String token = authHeader.substring(7);
        try {
            String userId = jwtProvider.getSubject(token);
            return repository.findById(userId)
                    .map(u -> new UserResponse(UUID.fromString(u.getId()), u.getEmail(), u.getDisplayName()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
