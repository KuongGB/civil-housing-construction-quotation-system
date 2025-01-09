package com.example.system.config;

import com.example.system.model.user.Role;
import com.example.system.model.user.User;
import com.example.system.repository.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Component
public class RunFileDB implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RunFileDB(JdbcTemplate jdbcTemplate, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        /* executeSQLScript("dataScript.sql");*/
        executeSQLScript("DataDemo1.sql");

        // Check if user exists by email
/*        Optional<User> userExist = userRepository.findByEmail("admin@gmail.com");
        // If user does not exist, create a new user
        if (!userExist.isPresent()) {*/
        User user = User.builder()
                .name("Admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("1"))
                .role(Role.ADMIN)
                .status(true)
                .phone("0000000000")
                .address("Admin")
                .gender(true)
                .birthday(LocalDate.parse("1998-05-06"))
                .build();
        userRepository.save(user);
        user = User.builder()
                .name("Manager")
                .email("manager@gmail.com")
                .password(passwordEncoder.encode("1"))
                .role(Role.MANAGER)
                .status(true)
                .phone("0000000000")
                .address("Manager")
                .gender(true)
                .birthday(LocalDate.parse("1999-11-10"))
                .build();
        userRepository.save(user);
        user = User.builder()
                .name("Hoang Nam")
                .email("customer@gmail.com")
                .password(passwordEncoder.encode("1"))
                .role(Role.CUSTOMER)
                .status(true)
                .phone("0779558502")
                .address("Ba ria - Vung Tau")
                .gender(true)
                .birthday(LocalDate.parse("2002-01-05"))
                .build();
        userRepository.save(user);

        /*        }*/
    }

    private void executeSQLScript(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        InputStream inputStream = resource.getInputStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder command = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.trim().startsWith("--")) { // Ignore empty lines and comments
                    command.append(line);
                    if (line.trim().endsWith(";")) {
                        jdbcTemplate.execute(command.toString());
                        command.setLength(0);
                    }
                }
            }
        }
    }
}
