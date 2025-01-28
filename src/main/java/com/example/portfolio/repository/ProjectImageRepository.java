package com.example.portfolio.repository;

import com.example.portfolio.entity.ProjectImage;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImage, UUID> {
    public Optional<ProjectImage> findById(UUID Id);
}
