package com.zherikhov.planningpoker.infrastructure.persistence.service;

import com.zherikhov.planningpoker.infrastructure.persistence.dao.RoomJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomJpaRepository repository;

    public RoomService(RoomJpaRepository repository) {
        this.repository = repository;
    }

    public RoomEntity save(RoomEntity room) {
        return repository.save(room);
    }

    public Optional<RoomEntity> findById(String id) {
        return repository.findById(id);
    }

    public List<RoomEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

