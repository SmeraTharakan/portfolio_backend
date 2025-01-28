package com.example.portfolio.controller;

import com.example.portfolio.entity.ProjectImage;
import com.example.portfolio.service.ProjectImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;
import java.util.List;


@CrossOrigin 
@RestController
@RequestMapping("/project-images")
public class ProjectImageController {

    private final ProjectImageService projectImageService;

    @Autowired
    public ProjectImageController(ProjectImageService projectImageService) {
        this.projectImageService = projectImageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProjectImage(@RequestParam("image") MultipartFile file) {
        try {
            String message = projectImageService.uploadProjectImage(file);
            return ResponseEntity.ok(message);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload image: " + e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProjectImage(@PathVariable UUID id) {
        try {
            String base64Image = projectImageService.getProjectImage(id);
            return ResponseEntity.ok(base64Image);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ProjectImage>> getAllProjectImages() {
        List<ProjectImage> projectImages = projectImageService.getAllProjectImages();
        if (projectImages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projectImages);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectImage(@PathVariable UUID id) {
        try {
            String message = projectImageService.deleteProjectImage(id);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}


