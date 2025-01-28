package com.example.portfolio.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "project_images")
@Data
public class ProjectImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Lob
    @Column(nullable = false)
    private byte[] image; 
}
