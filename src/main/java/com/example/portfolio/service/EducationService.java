package com.example.portfolio.service;

import com.example.portfolio.entity.Education;
import com.example.portfolio.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    public Education getEducationById(UUID id) {
        return educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education record not found with ID: " + id));
    }

    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    // Update an existing education record
    public Education updateEducation(UUID id, Education updatedEducation) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education record not found with ID: " + id));

        education.setDegree(updatedEducation.getDegree());
        education.setInstitute(updatedEducation.getInstitute());
        education.setStartYear(updatedEducation.getStartYear());
        education.setEndYear(updatedEducation.getEndYear());
        education.setMarks(updatedEducation.getMarks());

        return educationRepository.save(education);
    }

    // Delete an education record by ID
    public void deleteEducation(UUID id) {
        if (!educationRepository.existsById(id)) {
            throw new RuntimeException("Education record not found with ID: " + id);
        }
        educationRepository.deleteById(id);
    }
}
