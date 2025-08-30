package com.zherikhov.planningpoker.application.auth;

public record LoginResponse(String accessToken, int expiresIn, UserResponse user) {}
