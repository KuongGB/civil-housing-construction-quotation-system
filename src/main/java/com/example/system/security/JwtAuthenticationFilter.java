package com.example.system.security;

import com.example.system.repository.token.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    @Override
    protected void doFilterInternal(
            @org.springframework.lang.NonNull HttpServletRequest request,
            @org.springframework.lang.NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

//        HttpSession session = request.getSession();

        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        String accessToken;
        String refreshToken;
        String userEmailFromAT;
        String userEmailFromRT;
        if (jwtService.extractAccessTokenFromCookie(request) == null || jwtService.extractRefreshTokenFromCookie(request) == null){
            filterChain.doFilter(request,response);
            return;
        }
        try {
            accessToken = jwtService.extractAccessTokenFromCookie(request);
            refreshToken = jwtService.extractRefreshTokenFromCookie(request);
            userEmailFromAT = jwtService.extractUsername(accessToken);
            userEmailFromRT = jwtService.extractUsername(refreshToken);
            if (userEmailFromAT == null && userEmailFromRT == null){
                filterChain.doFilter(request,response);
                return;
            }
            if (!userEmailFromAT.equals(userEmailFromRT)){
                filterChain.doFilter(request,response);
                return;
            }
            if (jwtService.isTokenExpired(accessToken)) {
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmailFromRT);
                    var isTokenValid = tokenRepository.findByToken(refreshToken)
                            .map(t -> !t.isExpired() && !t.isRevoked())
                            .orElse(false);
                    if (jwtService.isTokenValid(refreshToken, userDetails) && isTokenValid) {
                        accessToken = jwtService.generateToken(userDetails);
                        if (accessToken != null) {
                            // Tạo cookie mới với token mới và cập nhật vào phản hồi
                            Cookie newTokenCookie = new Cookie("access_token", accessToken);
                            newTokenCookie.setHttpOnly(true);
                            newTokenCookie.setMaxAge(31536000);
                            newTokenCookie.setPath("/");
                            response.addCookie(newTokenCookie);
                        }
                    }
                }
            }
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmailFromAT);
                if (jwtService.isTokenValid(accessToken, userDetails) && jwtService.isWorkingSession(request)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException | NoSuchElementException e){
            filterChain.doFilter(request, response);
        }
    }
}