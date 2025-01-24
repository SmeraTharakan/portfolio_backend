package com.example.portfolio.service;

import com.example.portfolio.entity.ProjectImage;
import com.example.portfolio.repository.ProjectImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectImageService {

    private final ProjectImageRepository projectImageRepository;

    @Autowired
    public ProjectImageService(ProjectImageRepository projectImageRepository) {
        this.projectImageRepository = projectImageRepository;
    }

    public List<ProjectImage> getAllProjectImages() {
        return projectImageRepository.findAll();
    }

    public Optional<ProjectImage> getProjectImageById(Long imageId) {
        return projectImageRepository.findById(imageId);
    }
}
