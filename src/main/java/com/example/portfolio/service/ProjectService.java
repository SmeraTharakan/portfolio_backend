package com.example.portfolio.service;

import com.example.portfolio.entity.Projects;
import com.example.portfolio.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public Optional<Projects> getProjectByProjectId(UUID projectId) {
        return projectsRepository.findById(projectId);
    }

    public List<Projects> findAllProjects() {
        return projectsRepository.findAll();
    }

    public Projects createProject(Projects project) {
        return projectsRepository.save(project);
    }

    public Projects updateProject(UUID projectId, Projects updatedProject) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));
    
        project.setTitle(updatedProject.getTitle());
        project.setBrief(updatedProject.getBrief());
        project.setDescription(updatedProject.getDescription());
        project.setTechStack(updatedProject.getTechStack());
        project.setRepoUrl(updatedProject.getRepoUrl());
        project.setImageId(updatedProject.getImageId()); 
    
        return projectsRepository.save(project); 
    }
    

    public void deleteProject(UUID id) {
        if (!projectsRepository.existsById(id)) {
            throw new RuntimeException("Project not found with ID: " + id);
        }
        projectsRepository.deleteById(id);
    }
}
