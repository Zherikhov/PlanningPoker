package com.zherikhov.planningpoker.infrastructure.persistence.service;

import com.zherikhov.planningpoker.infrastructure.persistence.dao.AppUserJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.AppUserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserJpaRepository repository;

    public AppUserService(AppUserJpaRepository repository) {
        this.repository = repository;
    }

    public AppUserEntity save(AppUserEntity user) {
        return repository.save(user);
    }

    public Optional<AppUserEntity> findById(String id) {
        return repository.findById(id);
    }

    public Optional<AppUserEntity> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<AppUserEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

