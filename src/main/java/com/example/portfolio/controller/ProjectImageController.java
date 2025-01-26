package com.example.portfolio.controller;

import com.example.portfolio.entity.ProjectImage;
import com.example.portfolio.service.ProjectImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/project-images")
public class ProjectImageController {

    private final ProjectImageService projectImageService;

    @Autowired
    public ProjectImageController(ProjectImageService projectImageService) {
        this.projectImageService = projectImageService;
    }

    @GetMapping
    public List<ProjectImage> getAllProjectImages() {
        return projectImageService.getAllProjectImages();
    }

    @GetMapping("/{id}")
    public Optional<ProjectImage> getProjectImageById(@PathVariable Long id) {
        return projectImageService.getProjectImageById(id);
    }
}
