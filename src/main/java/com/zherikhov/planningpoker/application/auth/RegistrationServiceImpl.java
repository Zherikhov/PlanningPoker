package com.zherikhov.planningpoker.application.auth;

import com.zherikhov.planningpoker.domain.user.Role;
import com.zherikhov.planningpoker.domain.user.User;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.AppUserEntity;
import com.zherikhov.planningpoker.infrastructure.persistence.dao.AppUserJpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.time.OffsetDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final AppUserJpaRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public RegistrationServiceImpl(AppUserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User register(RegisterUser req) {
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
        return new User(UUID.fromString(saved.getId()), saved.getEmail(), saved.getDisplayName(), Role.valueOf(saved.getRole()));
    }
}
