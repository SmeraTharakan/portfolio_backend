package com.example.portfolio.service;

import com.example.portfolio.entity.AdminUsers;
import com.example.portfolio.repository.AdminUsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminUsersService {

    private final AdminUsersRepository adminUsersRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUsersService(AdminUsersRepository adminUsersRepository, PasswordEncoder passwordEncoder) {
        this.adminUsersRepository = adminUsersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AdminUsers> getAllAdminUsers() {
        return adminUsersRepository.findAll();
    }

    public Optional<AdminUsers> getAdminUserById(UUID id) {
        return adminUsersRepository.findById(id);
    }

    public Optional<AdminUsers> getAdminByUsername(String username) {
        return adminUsersRepository.findByUsername(username);
    }

    public AdminUsers createAdminUser(String username, String email, String password) {
        AdminUsers admin = new AdminUsers();
        admin.setId(UUID.randomUUID());
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));
        return adminUsersRepository.save(admin);
    }

    public Optional<AdminUsers> updateAdminUser(UUID id, AdminUsers updatedAdmin) {
        return adminUsersRepository.findById(id).map(admin -> {
            admin.setUsername(updatedAdmin.getUsername());
            admin.setEmail(updatedAdmin.getEmail());
            if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isEmpty()) {
                admin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
            }
            return adminUsersRepository.save(admin);
        });
    }

    public boolean deleteAdminUser(UUID id) {
        if (adminUsersRepository.existsById(id)) {
            adminUsersRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
