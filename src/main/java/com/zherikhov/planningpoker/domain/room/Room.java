package com.zherikhov.planningpoker.domain.room;

import com.zherikhov.planningpoker.domain.user.User;

import java.util.List;
import java.util.UUID;

/**
 * Domain aggregate representing a planning room.
 */
public record Room(
        UUID id,
        String name,
        String code,
        User owner,
        List<User> participants,
        List<Integer> deck
) {
}
