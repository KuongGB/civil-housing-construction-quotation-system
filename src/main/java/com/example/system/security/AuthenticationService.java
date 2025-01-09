package com.example.system.security;

import com.example.system.auth.AuthenticationRequest;
import com.example.system.auth.AuthenticationResponse;
import com.example.system.auth.RegisterRequest;
import com.example.system.mail.EmailSender;
import com.example.system.model.token.Token;
import com.example.system.model.token.TokenType;
import com.example.system.model.user.Role;
import com.example.system.model.user.User;
import com.example.system.repository.token.TokenRepository;
import com.example.system.repository.user.UserRepository;
import com.example.system.security.JwtService;
import com.example.system.validator.EmailValidator;
import com.example.system.validator.UserValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;

    public AuthenticationResponse register(RegisterRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email is not valid");
        }
        Optional<User> userexist = userRepository.findByEmail(request.getEmail());
        if (userexist.isPresent()) {
            return null;
        }
        var user = User.builder()
                .name(request.getFullname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(false)
                .role(Role.CUSTOMER)
                .phone(request.getPhone())
                .address(request.getAddress())
                .gender(request.isGender())
                .birthday(request.getBirthday())
                .build();
        var saveUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(saveUser, jwtToken);
        //String link = "http://localhost:3000/login?active=register&token=" + jwtToken;
        String link = "http://localhost:8080/confirm-register?token=" + jwtToken;
        String subject = "Confirm your email";
        emailSender.send(
                request.getEmail(),
                buildEmail(request.getFullname(), link),
                subject
        );
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        if (user.isStatus()) {
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .role(String.valueOf(user.getRole()))
                    .build();
        } else {
            throw new LockedException("");
        }
    }

    public void revokeAllUserTokens(User user) {
        var validUserToken = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }


    public boolean confirmToken(String token) {
        String userEmail = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return false;
        }
        if (!jwtService.isTokenValid(token, user)) {
            return false;
        }
        user.setStatus(true);
        userRepository.save(user);
        return true;
    }
    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\">Hi " + name + ",</p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\">Thank you for registering. Please click on the below link to activate your account:</p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\"><a href=\"" + link + "\">Activate Now</a></p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\">Link will expire in 5 minutes.</p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:16px;line-height:22px;color:#0b0c0c\">See you soon!</p>" +
                "</div>";
    }
}
