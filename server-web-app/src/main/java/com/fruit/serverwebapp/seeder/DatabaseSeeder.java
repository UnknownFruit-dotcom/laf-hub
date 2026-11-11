package com.fruit.serverwebapp.seeder;

import com.fruit.serverwebapp.dto.request.UserCreationRequest;
import com.fruit.serverwebapp.repository.UserRepository;
import com.fruit.serverwebapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserService userService;

    @Value("${admin.login}")
    private String login;

    @Value("${admin.password}")
    private String password;

    @Override
    public void run(String... args) {
        log.info("Database seeder running...");

        seedIfAbsent(login, password);
    }

    private void seedIfAbsent(String login, String password) {
        if (!userRepository.existsByLogin(login)) {
            userService.createAdmin(new UserCreationRequest(
                    login,
                    password));

            log.info("Seeded user {}", login);
        }
    }
}
