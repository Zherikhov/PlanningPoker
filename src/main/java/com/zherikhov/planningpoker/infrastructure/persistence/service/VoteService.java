package com.zherikhov.planningpoker.infrastructure.persistence.service;

import com.zherikhov.planningpoker.infrastructure.persistence.dao.VoteJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.VoteEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {
    private final VoteJpaRepository repository;

    public VoteService(VoteJpaRepository repository) {
        this.repository = repository;
    }

    public VoteEntity save(VoteEntity vote) {
        return repository.save(vote);
    }

    public Optional<VoteEntity> findById(String id) {
        return repository.findById(id);
    }

    public List<VoteEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

