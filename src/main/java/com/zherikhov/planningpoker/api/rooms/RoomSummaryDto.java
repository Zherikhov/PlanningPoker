package com.zherikhov.planningpoker.api.rooms;

import java.time.Instant;

/**
 * Summary information about a room returned to the client.
 */
public record RoomSummaryDto(
        String id,
        String name,
        String description,
        Owner owner,
        int tasksCount,
        Instant updatedAt,
        String code
) {
    public record Owner(String id, String displayName) {}
}

