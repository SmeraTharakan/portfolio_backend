package com.example.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "admin_users")
public class AdminUsers {

    @Id
    private UUID id;
    private String username;
    private String email;
    private String password;
}
