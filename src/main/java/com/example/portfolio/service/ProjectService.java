package com.example.portfolio.service;

import com.example.portfolio.entity.Projects;
import com.example.portfolio.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public List<Projects> getProjectsByUserId(Long userId) {
        return projectsRepository.findByUserId(userId);
    }

    public Optional<Projects> getProjectByProjectId(Long projectId) {
        return projectsRepository.findById(projectId);
    }
}
