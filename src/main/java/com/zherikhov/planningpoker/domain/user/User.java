package com.zherikhov.planningpoker.domain.user;

import java.util.UUID;

/**
 * Domain representation of an application user.
 */
public record User(
        UUID id,
        String email,
        String displayName,
        Role role
) {
}
