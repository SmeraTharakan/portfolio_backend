package com.example.portfolio.repository;

import com.example.portfolio.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {
}

