package com.example.portfolio.repository;

import com.example.portfolio.entity.Education;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EducationRepository extends JpaRepository<Education, UUID> {
}
