package com.zherikhov.planningpoker.application.auth;

import com.zherikhov.planningpoker.domain.user.Role;
import com.zherikhov.planningpoker.domain.user.User;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.AppUserEntity;
import com.zherikhov.planningpoker.infrastructure.persistence.dao.AppUserJpaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    private final AppUserJpaRepository repository = mock(AppUserJpaRepository.class);
    private final RegistrationService service = new RegistrationServiceImpl(repository);

    @Test
    void register_okFlow() {
        when(repository.existsByEmailIgnoreCase("new@example.com")).thenReturn(false);
        ArgumentCaptor<AppUserEntity> captor = ArgumentCaptor.forClass(AppUserEntity.class);
        AppUserEntity saved = new AppUserEntity();
        saved.setId(UUID.randomUUID().toString());
        saved.setEmail("new@example.com");
        saved.setPasswordHash("hash");
        saved.setDisplayName("Vlad");
        saved.setRole("USER");
        saved.setCreatedAt(OffsetDateTime.now());
        saved.setUpdatedAt(saved.getCreatedAt());
        when(repository.save(captor.capture())).thenReturn(saved);

        User res = service.register(new RegisterUser("new@example.com", "Secret123", "Vlad"));

        assertEquals("new@example.com", res.email());
        assertEquals("Vlad", res.displayName());
        assertEquals(Role.USER, res.role());
        AppUserEntity toSave = captor.getValue();
        assertEquals("USER", toSave.getRole());
        assertTrue(toSave.getPasswordHash() != null && !toSave.getPasswordHash().equals("Secret123"));
    }

    @Test
    void register_duplicateEmail() {
        when(repository.existsByEmailIgnoreCase("user@example.com")).thenReturn(true);
        assertThrows(EmailAlreadyExistsException.class,
                () -> service.register(new RegisterUser("user@example.com", "Secret123", "Vlad")));
        verify(repository, never()).save(any());
    }
}
