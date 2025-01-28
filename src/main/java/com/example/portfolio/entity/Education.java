package com.example.portfolio.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "education")
@Data
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false, length = 100)
    private String degree;

    @Column(nullable = false, length = 150)
    private String institute;

    @Column(nullable = false)
    private Integer startYear;

    @Column
    private Integer endYear;

    @Column(nullable = false)
    private Float marks;
}
