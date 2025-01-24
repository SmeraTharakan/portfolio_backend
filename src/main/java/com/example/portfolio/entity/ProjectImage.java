package com.example.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "project_images")
@Data
public class ProjectImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private byte[] image; 
}
