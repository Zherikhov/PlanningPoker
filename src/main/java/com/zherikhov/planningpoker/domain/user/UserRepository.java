package com.zherikhov.planningpoker.domain.user;

import java.util.Optional;

/**
 * Repository interface for accessing users.
 */
public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user, String passwordHash);
}
