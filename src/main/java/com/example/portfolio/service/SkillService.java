package com.example.portfolio.service;

import com.example.portfolio.entity.Skills;
import com.example.portfolio.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Skills getSkillById(UUID id) {
        return skillsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with ID: " + id));
    }

    public Skills createSkill(Skills skill) {
        return skillsRepository.save(skill);
    }

    public Skills updateSkill(UUID id, Skills updatedSkill) {
        Skills skill = skillsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with ID: " + id));

        skill.setCategory(updatedSkill.getCategory());
        skill.setName(updatedSkill.getName());

        return skillsRepository.save(skill);
    }

    public void deleteSkill(UUID id) {
        if (!skillsRepository.existsById(id)) {
            throw new RuntimeException("Skill not found with ID: " + id);
        }
        skillsRepository.deleteById(id);
    }
}
