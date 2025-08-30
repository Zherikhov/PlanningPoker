package com.zherikhov.planningpoker.infrastructure.persistence.service;

import com.zherikhov.planningpoker.infrastructure.persistence.dao.RoomDeckCardJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomDeckCardEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomDeckCardService {
    private final RoomDeckCardJpaRepository repository;

    public RoomDeckCardService(RoomDeckCardJpaRepository repository) {
        this.repository = repository;
    }

    public RoomDeckCardEntity save(RoomDeckCardEntity card) {
        return repository.save(card);
    }

    public Optional<RoomDeckCardEntity> findById(String id) {
        return repository.findById(id);
    }

    public List<RoomDeckCardEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

