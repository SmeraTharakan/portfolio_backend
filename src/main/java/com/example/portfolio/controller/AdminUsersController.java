package com.example.portfolio.controller;

import com.example.portfolio.entity.AdminUsers;
import com.example.portfolio.service.AdminUsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/users")
public class AdminUsersController {

    private final AdminUsersService adminUsersService;

    public AdminUsersController(AdminUsersService adminUsersService) {
        this.adminUsersService = adminUsersService;
    }

    @GetMapping
    public ResponseEntity<List<AdminUsers>> getAllAdminUsers() {
        return ResponseEntity.ok(adminUsersService.getAllAdminUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminUsers> getAdminUserById(@PathVariable UUID id) {
        Optional<AdminUsers> adminUser = adminUsersService.getAdminUserById(id);
        return adminUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AdminUsers> createAdminUser(@RequestBody AdminUsers adminUsers) {
        AdminUsers createdAdmin = adminUsersService.createAdminUser(adminUsers.getUsername(), adminUsers.getEmail(), adminUsers.getPassword());
        return ResponseEntity.ok(createdAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminUsers> updateAdminUser(@PathVariable UUID id, @RequestBody AdminUsers adminUsers) {
        Optional<AdminUsers> updatedAdmin = adminUsersService.updateAdminUser(id, adminUsers);
        return updatedAdmin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminUser(@PathVariable UUID id) {
        boolean deleted = adminUsersService.deleteAdminUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
