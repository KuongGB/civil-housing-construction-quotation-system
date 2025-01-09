package com.example.system.controller;

import com.example.system.auth.AuthenticationRequest;
import com.example.system.auth.AuthenticationResponse;
import com.example.system.auth.RegisterRequest;
import com.example.system.model.user.User;
import com.example.system.repository.user.UserRepository;
import com.example.system.security.AuthenticationService;
import com.example.system.security.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        AuthenticationResponse a = authenticationService.register(request);
        if (null == a) {
            return ResponseEntity.status(400).body("Email is existed!");
        }
        return ResponseEntity.ok(a);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse a;
        try {
            a = authenticationService.authenticate(request);
        } catch (AuthenticationException e) {
            if (e instanceof LockedException) {
                return ResponseEntity.status(400)
                        .body("Your account was disabled!");
            } else {
                return ResponseEntity.status(400)
                        .body("The Username or Password is Incorrect!");
            }
        }
        ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", a.getAccessToken())
                .httpOnly(true)
                .maxAge(604800) // 1 week
                .path("/")
                .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", a.getRefreshToken())
                .httpOnly(true)
                .maxAge(604800) // 1 week
                .path("/")
                .build();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(a);
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        // Lấy access token và refresh token từ cookie
        Cookie[] cookies = request.getCookies();
        String accessToken = null;
        String refreshToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("access_token")) {
                    accessToken = cookie.getValue();
                } else if (cookie.getName().equals("refresh_token")) {
                    refreshToken = cookie.getValue();
                }
            }
        }

        // Kiểm tra nếu không tìm thấy refresh token trong cookie
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token not found in cookie");
        }

        // Xác định user từ refresh token và kiểm tra tính hợp lệ của token
        String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        // Lấy thông tin user từ cơ sở dữ liệu
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        // Kiểm tra tính hợp lệ của refresh token
        if (!jwtService.isTokenValid(refreshToken, user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        // Tạo access token mới
        String newAccessToken = jwtService.generateToken(user);

        // Lưu trạng thái mới của user
        authenticationService.revokeAllUserTokens(user);
        authenticationService.saveUserToken(user, newAccessToken);

        // Cập nhật cookie với access token mới
        Cookie newAccessTokenCookie = new Cookie("access_token", newAccessToken);
        newAccessTokenCookie.setHttpOnly(true);
        newAccessTokenCookie.setMaxAge(604800); // 1 week
        newAccessTokenCookie.setPath("/");
        response.addCookie(newAccessTokenCookie);

        // Trả về phản hồi thành công
        return ResponseEntity.ok().build();
    }

        @GetMapping("confirm")
        public ResponseEntity<?> confirm(@RequestParam("token") String token) {
            boolean confirm = authenticationService.confirmToken(token);
            if (confirm) {
                return ResponseEntity.status(200).body("Active account successfully");
            }
            return ResponseEntity.status(400).body("Link expired");
        }
}
