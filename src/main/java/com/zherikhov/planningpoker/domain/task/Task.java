package com.zherikhov.planningpoker.domain.task;

import java.util.UUID;

/**
 * Domain aggregate for a planning task.
 */
public record Task(
        UUID id,
        UUID roomId,
        String title,
        String description,
        UUID authorUserId,
        TaskStatus status,
        Integer finalValue
) {
}
