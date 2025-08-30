package com.zherikhov.planningpoker.application.auth;

/**
 * Command object for registering a new user.
 */
public record RegisterUser(String email, String password, String displayName) {}
