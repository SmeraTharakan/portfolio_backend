package com.example.portfolio.controller;

import com.example.portfolio.entity.Projects;
import com.example.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public Optional<Projects> getProjectById(@PathVariable Long id) {
        return projectService.getProjectByProjectId(id);
    }

    @GetMapping
    public List<Projects> getAllProjects() {
        return projectService.findAllProjects();
    }
}
