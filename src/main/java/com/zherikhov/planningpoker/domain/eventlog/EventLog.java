package com.zherikhov.planningpoker.domain.eventlog;

import java.time.Instant;
import java.util.UUID;

/**
 * Technical event captured for auditing.
 */
public record EventLog(
        UUID id,
        String type,
        String payload,
        Instant timestamp
) {
}
