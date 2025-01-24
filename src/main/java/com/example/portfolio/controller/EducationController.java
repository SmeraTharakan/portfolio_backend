package com.example.portfolio.controller;

import com.example.portfolio.entity.Education;
import com.example.portfolio.service.EducationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/educations")
@RestController
public class EducationController {

    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<Education> getAllEducation() {
        return educationService.getAllEducation();
    }
}
