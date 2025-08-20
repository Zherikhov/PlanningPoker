package com.zherikhov.planningpoker.infrastructure.persistence.service;

import com.zherikhov.planningpoker.infrastructure.persistence.dao.PlanningTaskJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.PlanningTaskEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanningTaskService {
    private final PlanningTaskJpaRepository repository;

    public PlanningTaskService(PlanningTaskJpaRepository repository) {
        this.repository = repository;
    }

    public PlanningTaskEntity save(PlanningTaskEntity task) {
        return repository.save(task);
    }

    public Optional<PlanningTaskEntity> findById(String id) {
        return repository.findById(id);
    }

    public List<PlanningTaskEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

