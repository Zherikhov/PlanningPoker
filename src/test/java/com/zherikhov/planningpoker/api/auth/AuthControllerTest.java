package com.zherikhov.planningpoker.api.auth;

import com.zherikhov.planningpoker.application.auth.EmailAlreadyExistsException;
import com.zherikhov.planningpoker.application.auth.UserResponse;
import com.zherikhov.planningpoker.application.auth.AuthService;
import com.zherikhov.planningpoker.application.auth.RegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    @MockBean
    private AuthService authService;

    @Test
    void register_ReturnsCreatedUser() throws Exception {
        when(registrationService.register(any())).thenReturn(new UserResponse(UUID.randomUUID(), "new@example.com", "Vlad"));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"new@example.com\",\"password\":\"Secret123\",\"displayName\":\"Vlad\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("new@example.com"))
                .andExpect(jsonPath("$.displayName").value("Vlad"));
    }

    @Test
    void register_DuplicateEmail_ReturnsConflict() throws Exception {
        Mockito.when(registrationService.register(any()))
                .thenThrow(new EmailAlreadyExistsException("user@example.com"));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@example.com\",\"password\":\"Secret123\",\"displayName\":\"Vlad\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("EMAIL_EXISTS"));
    }

    @Test
    void register_InvalidRequest_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"bad\",\"password\":\"123\",\"displayName\":\"V\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("VALIDATION_ERROR"));
    }

    @Test
    void logout_ClearsRefreshCookie() throws Exception {
        mockMvc.perform(post("/api/auth/logout"))
                .andExpect(status().isNoContent())
                .andExpect(header().string(HttpHeaders.SET_COOKIE, containsString("Max-Age=0")))
                .andExpect(header().string(HttpHeaders.SET_COOKIE, containsString("refreshToken=")));
    }
}
