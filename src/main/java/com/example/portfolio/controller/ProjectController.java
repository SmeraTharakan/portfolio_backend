package com.example.portfolio.controller;

import com.example.portfolio.entity.Projects;
import com.example.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public Optional<Projects> getProjectById(@PathVariable UUID id) {
        return projectService.getProjectByProjectId(id);
    }

    @GetMapping
    public List<Projects> getAllProjects() {
        return projectService.findAllProjects();
    }

    @PostMapping
    public ResponseEntity<Projects> createProject(@RequestBody Projects project) {
        Projects createdProject = projectService.createProject(project);
        return ResponseEntity.ok(createdProject);
    }

    // Update an existing project
    @PutMapping("/{id}")
    public ResponseEntity<Projects> updateProject(@PathVariable UUID id, @RequestBody Projects updatedProject) {
        try {
            Projects project = projectService.updateProject(id, updatedProject);
            return ResponseEntity.ok(project);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
