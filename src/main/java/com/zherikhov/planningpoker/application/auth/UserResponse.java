package com.zherikhov.planningpoker.application.auth;

import java.util.UUID;

public record UserResponse(UUID id, String email, String displayName) {}
