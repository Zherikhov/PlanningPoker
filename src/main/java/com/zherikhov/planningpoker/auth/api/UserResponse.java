package com.zherikhov.planningpoker.auth.api;

import java.util.UUID;

public record UserResponse(UUID id, String email, String displayName) {}
