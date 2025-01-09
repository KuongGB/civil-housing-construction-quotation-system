package com.example.system.security;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class TokenRefreshScheduler {
    private final String REFRESH_TOKEN_URL = "http://localhost:8080/api/v1/auth/refresh-token";//đây chưa phải api

    @Autowired
    private RestTemplate restTemplate;

    // Gọi API refresh-token mỗi 4 phút
    @Scheduled(fixedRate = 4 * 60 * 1000) // 4 phút
    public void refreshToken() {
        // Gửi yêu cầu refresh token không cần thông tin của request
//        restTemplate.postForEntity(REFRESH_TOKEN_URL, null, Void.class);
    }
}

