package com.zherikhov.planningpoker.users.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserEntity, String> {
    boolean existsByEmailIgnoreCase(String email);
    Optional<AppUserEntity> findByEmailIgnoreCase(String email);
    Optional<AppUserEntity> findByEmail(String email);
}

