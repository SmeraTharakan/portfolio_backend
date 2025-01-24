package com.example.portfolio.repository;


import com.example.portfolio.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    public Optional<Projects> findById(Long Id);
}
