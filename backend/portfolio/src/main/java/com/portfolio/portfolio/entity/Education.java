package com.portfolio.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "education")
@Data
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
