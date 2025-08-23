package com.zherikhov.planningpoker.api.rooms;

import com.zherikhov.planningpoker.domain.votes.DeckType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request payload for creating a room.
 */
public record CreateRoomRequest(
        @NotBlank @Size(min = 1, max = 80) String name,
        @Size(max = 500) String description,
        DeckType deckType,
        boolean allowGuests
) {}

