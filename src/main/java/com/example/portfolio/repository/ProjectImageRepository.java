package com.example.portfolio.repository;

import com.example.portfolio.entity.ProjectImage;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {
    public Optional<ProjectImage> findById(Long Id);
}
