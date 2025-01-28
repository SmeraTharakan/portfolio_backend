package com.example.portfolio.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "projects")
@Data
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String brief;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String techStack;

    @Column(columnDefinition = "TEXT")
    private String repoUrl;

    @Column(name = "project_image_id")
    private UUID imageId; 

}
