package com.example.portfolio.controller;

import com.example.portfolio.entity.Education;
import com.example.portfolio.service.EducationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
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

    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable UUID id) {
        try {
            Education education = educationService.getEducationById(id);
            return ResponseEntity.ok(education);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new education record
    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody Education education) {
        Education createdEducation = educationService.createEducation(education);
        return ResponseEntity.ok(createdEducation);
    }

    // Update an existing education record
    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable UUID id, @RequestBody Education updatedEducation) {
        try {
            Education education = educationService.updateEducation(id, updatedEducation);
            return ResponseEntity.ok(education);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an education record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable UUID id) {
        try {
            educationService.deleteEducation(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
