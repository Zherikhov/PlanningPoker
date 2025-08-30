package com.zherikhov.planningpoker.api.auth;

public record LoginResponse(String accessToken, int expiresIn, UserResponse user) {}
