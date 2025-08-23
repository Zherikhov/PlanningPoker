package com.zherikhov.planningpoker.api.auth;

import com.zherikhov.planningpoker.infrastructure.security.JwtProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Basic authentication controller providing login and refresh endpoints.
 * This is a simplified example and not production ready.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtProvider jwtProvider;

    public AuthController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    private static final String DEMO_EMAIL = "user@example.com";
    private static final String DEMO_PASSWORD = "secret";
    private static final UserDto DEMO_USER = new UserDto("1", DEMO_EMAIL, "Demo User");

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        if (DEMO_EMAIL.equals(request.email()) && DEMO_PASSWORD.equals(request.password())) {
            String token = jwtProvider.generateToken(DEMO_USER.id());
            if (request.rememberMe()) {
                Cookie cookie = new Cookie("refreshToken", token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/api/auth/refresh");
                cookie.setMaxAge(2592000); // 30 days
                response.addCookie(cookie);
            }
            LoginResponse body = new LoginResponse(token, 3600, DEMO_USER);
            return ResponseEntity.ok(body);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "error", "INVALID_CREDENTIALS",
                        "message", "Email or password is incorrect"
                ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "NO_REFRESH", "message", "Missing refresh token"));
        }
        String token = jwtProvider.generateToken(DEMO_USER.id());
        return ResponseEntity.ok(Map.of("accessToken", token, "expiresIn", 3600));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", "");
        cookie.setPath("/api/auth/refresh");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authHeader.substring(7);
        try {
            jwtProvider.getSubject(token);
            return ResponseEntity.ok(Map.of("user", DEMO_USER));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // request/response records
    public record LoginRequest(String email, String password, boolean rememberMe) {}
    public record UserDto(String id, String email, String displayName) {}
    public record LoginResponse(String accessToken, int expiresIn, UserDto user) {}
}
