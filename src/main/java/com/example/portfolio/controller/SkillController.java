package com.example.portfolio.controller;

import com.example.portfolio.entity.Skills;
import com.example.portfolio.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
