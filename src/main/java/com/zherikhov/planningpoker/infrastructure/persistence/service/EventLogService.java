package com.zherikhov.planningpoker.infrastructure.persistence.service;

import com.zherikhov.planningpoker.infrastructure.persistence.dao.EventLogJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.EventLogEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventLogService {
    private final EventLogJpaRepository repository;

    public EventLogService(EventLogJpaRepository repository) {
        this.repository = repository;
    }

    public EventLogEntity save(EventLogEntity eventLog) {
        return repository.save(eventLog);
    }

    public Optional<EventLogEntity> findById(Long id) {
        return repository.findById(id);
    }

    public List<EventLogEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

