package com.example.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "users") 
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 15)
    private String phone;

    @Lob
    private byte[] profilePicture; 

    @Column(length = 100)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String linkedinUrl;

    @Column(columnDefinition = "TEXT")
    private String githubUrl;

    public byte[] getProfilePicture() {
        return profilePicture;
    }
}
