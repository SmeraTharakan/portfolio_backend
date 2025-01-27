package com.example.portfolio.controller;

import com.example.portfolio.entity.ProjectImage;
import com.example.portfolio.repository.ProjectImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;


@CrossOrigin 
@RestController
@RequestMapping("/project-images")
public class ProjectImageController {

    @Autowired
    private ProjectImageRepository projectImageRepository;

    // Upload project image
    @PostMapping("/upload")
    public ResponseEntity<String> uploadProjectImage(@RequestParam("image") MultipartFile file) {
        try {
            ProjectImage projectImage = new ProjectImage();
            projectImage.setImage(file.getBytes());

            projectImageRepository.save(projectImage);

            return ResponseEntity.ok("Project image uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload image: " + e.getMessage());
        }
    }

    // Get project image by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getProjectImage(@PathVariable Long id) {
        ProjectImage projectImage = projectImageRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Project image not found with ID: " + id));

        byte[] imageBytes = projectImage.getImage();

        if (imageBytes == null) {
            return ResponseEntity.status(404).body(null);
        }

        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return ResponseEntity.ok(base64Image);
    }
}


