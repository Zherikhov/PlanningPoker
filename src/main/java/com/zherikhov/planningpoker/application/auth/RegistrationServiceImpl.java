package com.zherikhov.planningpoker.application.auth;

import com.zherikhov.planningpoker.domain.user.EmailAlreadyExistsException;
import com.zherikhov.planningpoker.domain.user.Role;
import com.zherikhov.planningpoker.domain.user.User;
import com.zherikhov.planningpoker.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public RegistrationServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public User register(String email, String displayName, String password) {
        String normalizedEmail = email.trim().toLowerCase();
        String trimmedDisplayName = displayName.trim();
        if (repository.findByEmail(normalizedEmail).isPresent()) {
            throw new EmailAlreadyExistsException(normalizedEmail);
        }
        String hash = encoder.encode(password);
        User user = new User(UUID.randomUUID(), normalizedEmail, trimmedDisplayName, Role.USER);
        return repository.save(user, hash);
    }
}
