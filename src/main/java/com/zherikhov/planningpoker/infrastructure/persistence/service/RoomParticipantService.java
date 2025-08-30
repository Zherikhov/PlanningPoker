package com.zherikhov.planningpoker.infrastructure.persistence.service;

import com.zherikhov.planningpoker.infrastructure.persistence.dao.RoomParticipantJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomParticipantEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomParticipantService {
    private final RoomParticipantJpaRepository repository;

    public RoomParticipantService(RoomParticipantJpaRepository repository) {
        this.repository = repository;
    }

    public RoomParticipantEntity save(RoomParticipantEntity participant) {
        return repository.save(participant);
    }

    public Optional<RoomParticipantEntity> findById(String id) {
        return repository.findById(id);
    }

    public List<RoomParticipantEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

