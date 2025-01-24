package com.example.portfolio.service;

import com.example.portfolio.entity.Projects;
import com.example.portfolio.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public Optional<Projects> getProjectByProjectId(Long projectId) {
        return projectsRepository.findById(projectId);
    }

    public List<Projects> findAllProjects() {
        return projectsRepository.findAll();
    }
}
