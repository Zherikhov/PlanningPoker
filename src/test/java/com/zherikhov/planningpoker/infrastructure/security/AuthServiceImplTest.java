package com.zherikhov.planningpoker.infrastructure.security;

import com.zherikhov.planningpoker.application.auth.LoginRequest;
import com.zherikhov.planningpoker.application.auth.LoginResponse;
import com.zherikhov.planningpoker.infrastructure.persistence.dao.AppUserJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {
    private final AppUserJpaRepository repository = mock(AppUserJpaRepository.class);
    private final JwtProvider jwtProvider = new JwtProvider();
    private final AuthServiceImpl service = new AuthServiceImpl(jwtProvider, repository);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    void login_validCredentials_returnsUser() {
        AppUserEntity entity = new AppUserEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setEmail("user@example.com");
        entity.setPasswordHash(encoder.encode("Secret123"));
        entity.setDisplayName("Vlad");
        entity.setRole("USER");
        OffsetDateTime now = OffsetDateTime.now();
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        when(repository.findByEmailIgnoreCase("user@example.com")).thenReturn(Optional.of(entity));

        Optional<LoginResponse> res = service.login(new LoginRequest("user@example.com", "Secret123", false));

        assertTrue(res.isPresent());
        assertEquals("user@example.com", res.get().user().email());
    }

    @Test
    void login_invalidPassword_returnsEmpty() {
        AppUserEntity entity = new AppUserEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setEmail("user@example.com");
        entity.setPasswordHash(encoder.encode("Secret123"));
        entity.setDisplayName("Vlad");
        entity.setRole("USER");
        OffsetDateTime now = OffsetDateTime.now();
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        when(repository.findByEmailIgnoreCase("user@example.com")).thenReturn(Optional.of(entity));

        Optional<LoginResponse> res = service.login(new LoginRequest("user@example.com", "wrong", false));

        assertTrue(res.isEmpty());
    }

    @Test
    void login_unknownEmail_returnsEmpty() {
        when(repository.findByEmailIgnoreCase("user@example.com")).thenReturn(Optional.empty());

        Optional<LoginResponse> res = service.login(new LoginRequest("user@example.com", "Secret123", false));

        assertTrue(res.isEmpty());
    }
}
