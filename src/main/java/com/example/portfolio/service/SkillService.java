package com.example.portfolio.service;

import com.example.portfolio.entity.Skills;
import com.example.portfolio.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillsRepository skillsRepository;

    @Autowired
    public SkillService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }
}
