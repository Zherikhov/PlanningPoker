package com.zherikhov.planningpoker.tasks.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanningTaskRepository extends JpaRepository<PlanningTaskEntity, String> {
}

