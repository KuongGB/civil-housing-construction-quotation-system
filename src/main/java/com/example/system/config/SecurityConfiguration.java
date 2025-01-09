package com.example.system.config;

import com.example.system.model.user.Permission;
import com.example.system.model.user.Role;
import com.example.system.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    public static final String LOGOUT_URL = "/api/v1/auth/logout";
    public static final String LOGIN_URL = "http://localhost:3000/login";
    public static final String[] ENDPOINTS_WHITELIST = {
            "http://localhost:3000/login",
            "/logout", "/swagger-ui/**",
            "/swagger-resources/*",
            "/v3/api-docs/**",
            "/api/v1/auth/**",
            "/confirm-register/**"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(c -> c
                .configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "PATCH", "HEAD", "OPTIONS"));
                    config.setAllowedHeaders(Arrays.asList("*"));
                    config.addExposedHeader("Content-Type");
                    config.addExposedHeader("Authorization");
                    config.addExposedHeader("Access-Control-Allow-Origin");
                    config.setAllowCredentials(true); // Cho phép gửi cookie trong các yêu cầu CORS
                    return config;
                })
        );
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                        //User
//                        .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/user/profile/update").hasRole(Role.CUSTOMER.name())
//                        .requestMatchers(HttpMethod.PUT, "/user/update-role").hasRole(Role.ADMIN.name())
                        .requestMatchers("/user/**").permitAll()
                        //Combo building
//                        .requestMatchers(HttpMethod.GET, "/combobuilding/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/combobuilding/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name(), Role.CUSTOMER.name())
//                        .requestMatchers(HttpMethod.PUT, "/combobuilding/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                        .requestMatchers("/combobuilding/**").permitAll()
                        //Dashboard
                        .requestMatchers("/dashboard/**").permitAll()
                        //Blog
                        .requestMatchers("/blog/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/blog/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/blog/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
//                        .requestMatchers(HttpMethod.POST, "/blog/**").hasAnyAuthority(Permission.ADMIN_FULLACCESS.name(), Permission.MANAGER_FULLACCSESS.name())
//                        .requestMatchers(HttpMethod.PUT, "/blog/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                        /// ----------------------Cường chơi dưới này, đừng đụng trên của t nha
                        //Custom combo building
                        //.requestMatchers(HttpMethod.GET, "/custom-combo/**").permitAll()
                        .requestMatchers("/custom-combo/**").permitAll()
                        //Building
                        .requestMatchers("/building/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/building/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/building/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name(), Role.CUSTOMER.name())
//                        .requestMatchers(HttpMethod.PUT, "/building/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
//                        .requestMatchers("/building/form-consultant/**").permitAll()
                        //Request contract
                        .requestMatchers("/request-contract/**").permitAll()
//                                .requestMatchers(HttpMethod.GET,"/request-contract/**").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/request-contract/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name(), Role.CUSTOMER.name())
//                                .requestMatchers(HttpMethod.PUT, "/request-contract/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                        //Payment
                        .requestMatchers("/payment/**").permitAll()
                        .anyRequest().authenticated())

                .formLogin(form -> form // Cấu hình xác thực dựa trên biểu mẫu (form-based authentication)
                        .loginPage(LOGIN_URL) // Xác định trang đăng nhập của ứng dụng
                ) // URL mặc định sau khi đăng nhập thành công
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl(LOGOUT_URL) // URL để xử lý quá trình đăng xuất
/*                        .logoutSuccessUrl(LOGIN_URL) // URL mặc định sau khi đăng xuất thành công*/
                        .invalidateHttpSession(true) // Hủy bỏ phiên làm việc của người dùng sau khi đăng xuất
                        .clearAuthentication(true).deleteCookies("access_token").deleteCookies("refresh_token").addLogoutHandler(logoutHandler))// Xóa thông tin xác thực của người dùng sau khi đăng xuất
        ;
        return http.build();
    }

}