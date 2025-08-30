package com.zherikhov.planningpoker.application.auth;

import com.zherikhov.planningpoker.domain.user.User;

public interface RegistrationService {
    User register(String email, String displayName, String password);
}
