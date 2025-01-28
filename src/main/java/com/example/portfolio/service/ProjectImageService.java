package com.example.portfolio.service;

import com.example.portfolio.entity.ProjectImage;
import com.example.portfolio.repository.ProjectImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<ProjectImage> getProjectImageById(UUID imageId) {
        return projectImageRepository.findById(imageId);
    }

    public String uploadProjectImage(MultipartFile file) throws IOException {
        ProjectImage projectImage = new ProjectImage();
        projectImage.setImage(file.getBytes());

        projectImageRepository.save(projectImage);

        return "Project image uploaded successfully!";
    }

    public String getProjectImage(UUID id) {
        ProjectImage projectImage = projectImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project image not found with ID: " + id));

        byte[] imageBytes = projectImage.getImage();

        if (imageBytes == null) {
            throw new RuntimeException("No image found for ID: " + id);
        }

        return Base64.getEncoder().encodeToString(imageBytes);
    }
    
    public String deleteProjectImage(UUID id) {
        if (!projectImageRepository.existsById(id)) {
            throw new RuntimeException("Project image not found with ID: " + id);
        }

        projectImageRepository.deleteById(id);
        return "Project image deleted successfully!";
    }
}
