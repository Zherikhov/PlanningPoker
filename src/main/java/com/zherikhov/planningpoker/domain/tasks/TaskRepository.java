package com.zherikhov.planningpoker.domain.tasks;

import java.util.Optional;

public interface TaskRepository {
    PlanningTask save(PlanningTask task);
    Optional<PlanningTask> findById(String id);
}
