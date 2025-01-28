package com.example.portfolio.controller;

import com.example.portfolio.entity.Skills;
import com.example.portfolio.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequestMapping("/skills")
@RestController
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skills> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skills> getSkillById(@PathVariable UUID id) {
        try {
            Skills skill = skillService.getSkillById(id);
            return ResponseEntity.ok(skill);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Skills> createSkill(@RequestBody Skills skill) {
        Skills createdSkill = skillService.createSkill(skill);
        return ResponseEntity.ok(createdSkill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skills> updateSkill(@PathVariable UUID id, @RequestBody Skills updatedSkill) {
        try {
            Skills skill = skillService.updateSkill(id, updatedSkill);
            return ResponseEntity.ok(skill);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable UUID id) {
        try {
            skillService.deleteSkill(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
