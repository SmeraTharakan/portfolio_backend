package com.example.portfolio.config;

import com.example.portfolio.entity.AdminUsers;
import com.example.portfolio.repository.AdminUsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner initAdmin(AdminUsersRepository adminUsersRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (adminUsersRepository.count() == 0) {
                AdminUsers admin = new AdminUsers();
                admin.setId(UUID.randomUUID()); 
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin"));
                
                adminUsersRepository.save(admin);
                System.out.println("Admin user created: admin@example.com / admin");
            }
        };
    }
}
