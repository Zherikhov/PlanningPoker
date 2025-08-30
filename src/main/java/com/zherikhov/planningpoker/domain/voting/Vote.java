package com.zherikhov.planningpoker.domain.voting;

import java.util.UUID;

/**
 * Represents a participant's vote for a task.
 */
public record Vote(
        UUID taskId,
        UUID participantId,
        Integer value
) {
}
