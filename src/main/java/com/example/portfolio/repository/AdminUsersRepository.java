package com.example.portfolio.repository;

import com.example.portfolio.entity.AdminUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface AdminUsersRepository extends JpaRepository<AdminUsers, UUID> {
    Optional<AdminUsers> findByUsername(String username);
}
