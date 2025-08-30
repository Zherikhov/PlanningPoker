package com.zherikhov.planningpoker.api.auth;

public record LoginRequest(String email, String password, boolean rememberMe) {}
