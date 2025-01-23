package com.example.portfolio.repository;

import com.example.portfolio.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {
    List<Skills> findByUserId(Long userId); 
}

