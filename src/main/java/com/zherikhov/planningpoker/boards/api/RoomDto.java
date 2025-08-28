package com.zherikhov.planningpoker.boards.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data transfer object for room information.
 * <p>
 * Name is validated to ensure the client provides a meaningful title
 * when creating a new room.
 */
public record RoomDto(
        String id,
        @NotBlank @Size(min = 1, max = 80) String name,
        String status
) {
}
