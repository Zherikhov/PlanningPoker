package com.zherikhov.planningpoker.infrastructure.persistence.dao;

import com.zherikhov.planningpoker.infrastructure.persistence.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserJpaRepository extends JpaRepository<AppUserEntity, String> {
    boolean existsByEmailIgnoreCase(String email);
    Optional<AppUserEntity> findByEmailIgnoreCase(String email);
    Optional<AppUserEntity> findByEmail(String email);
}

