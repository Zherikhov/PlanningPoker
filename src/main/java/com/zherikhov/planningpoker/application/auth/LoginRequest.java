package com.zherikhov.planningpoker.application.auth;

public record LoginRequest(String email, String password, boolean rememberMe) {}
