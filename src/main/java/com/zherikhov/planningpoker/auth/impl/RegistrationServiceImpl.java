package com.zherikhov.planningpoker.auth.impl;

import com.zherikhov.planningpoker.auth.api.EmailAlreadyExistsException;
import com.zherikhov.planningpoker.auth.api.RegisterRequest;
import com.zherikhov.planningpoker.auth.api.UserResponse;
import com.zherikhov.planningpoker.users.persistence.AppUserEntity;
import com.zherikhov.planningpoker.users.persistence.AppUserRepository;
import com.zherikhov.planningpoker.auth.api.RegistrationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.time.OffsetDateTime;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final AppUserRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public RegistrationServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserResponse register(RegisterRequest req) {
        String email = req.email().trim().toLowerCase();
        String displayName = req.displayName().trim();
        if (repository.existsByEmailIgnoreCase(email)) {
            throw new EmailAlreadyExistsException(email);
        }
        String hash = encoder.encode(req.password());
        AppUserEntity entity = new AppUserEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setEmail(email);
        entity.setPasswordHash(hash);
        entity.setDisplayName(displayName);
        entity.setRole("USER");
        OffsetDateTime now = OffsetDateTime.now();
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        AppUserEntity saved = repository.save(entity);
        return new UserResponse(UUID.fromString(saved.getId()), saved.getEmail(), saved.getDisplayName());
    }
}
