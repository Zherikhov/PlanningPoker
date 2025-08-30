package com.zherikhov.planningpoker.infrastructure.persistence;

import com.zherikhov.planningpoker.domain.user.Role;
import com.zherikhov.planningpoker.domain.user.User;
import com.zherikhov.planningpoker.domain.user.UserRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.dao.AppUserJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.AppUserEntity;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final AppUserJpaRepository repository;

    public UserRepositoryImpl(AppUserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmailIgnoreCase(email).map(this::toDomain);
    }

    @Override
    public User save(User user, String passwordHash) {
        AppUserEntity entity = new AppUserEntity();
        entity.setId(user.id().toString());
        entity.setEmail(user.email());
        entity.setPasswordHash(passwordHash);
        entity.setDisplayName(user.displayName());
        entity.setRole(user.role().name());
        OffsetDateTime now = OffsetDateTime.now();
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        AppUserEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    private User toDomain(AppUserEntity entity) {
        return new User(UUID.fromString(entity.getId()), entity.getEmail(), entity.getDisplayName(),
                Role.valueOf(entity.getRole()));
    }
}
