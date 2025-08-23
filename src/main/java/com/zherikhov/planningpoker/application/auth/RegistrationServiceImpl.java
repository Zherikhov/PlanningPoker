package com.zherikhov.planningpoker.application.auth;

import com.zherikhov.planningpoker.api.auth.EmailAlreadyExistsException;
import com.zherikhov.planningpoker.api.auth.RegisterRequest;
import com.zherikhov.planningpoker.api.auth.UserResponse;
import com.zherikhov.planningpoker.domain.users.AppUserEntity;
import com.zherikhov.planningpoker.domain.users.AppUserJpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final AppUserJpaRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public RegistrationServiceImpl(AppUserJpaRepository repository) {
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
        AppUserEntity entity = new AppUserEntity(UUID.randomUUID(), email, hash, displayName);
        AppUserEntity saved = repository.save(entity);
        return new UserResponse(saved.getId(), saved.getEmail(), saved.getDisplayName());
    }
}
