package com.example.portfolio.repository;


import com.example.portfolio.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, UUID> {
    public Optional<Projects> findById(UUID Id);
}
