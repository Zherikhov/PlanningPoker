package com.zherikhov.planningpoker.api.auth;

import java.util.UUID;

public record UserResponse(UUID id, String email, String displayName) {}
