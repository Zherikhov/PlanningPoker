package com.zherikhov.planningpoker.infrastructure.persistence.dao;

import com.zherikhov.planningpoker.infrastructure.persistence.entity.PlanningTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanningTaskJpaRepository extends JpaRepository<PlanningTaskEntity, String> {
}

