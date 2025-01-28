package com.example.portfolio.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.portfolio.repository.UserRepository;
import com.example.portfolio.entity.User;
import java.util.Base64;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(UUID id, User userDetails) {
        User existingUser = getUserById(id); 
        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPhone(userDetails.getPhone());
        existingUser.setBio(userDetails.getBio());
        existingUser.setProfilePicture(userDetails.getProfilePicture());
        existingUser.setLocation(userDetails.getLocation());
        existingUser.setLinkedinUrl(userDetails.getLinkedinUrl());
        existingUser.setGithubUrl(userDetails.getGithubUrl());
        return userRepository.save(existingUser);
    }

    public void deleteUser(UUID id) {
        User existingUser = getUserById(id); 
        userRepository.delete(existingUser);
    }

    public String uploadProfilePicture(UUID id, MultipartFile file) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        user.setProfilePicture(file.getBytes());
        userRepository.save(user);

        return "Profile picture uploaded successfully!";
    }

    public String getProfilePicture(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        byte[] profilePicture = user.getProfilePicture();
        if (profilePicture == null) {
            throw new RuntimeException("No profile picture found for user with ID: " + id);
        }

        return Base64.getEncoder().encodeToString(profilePicture);
    }

    public String deleteProfilePicture(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    
        if (user.getProfilePicture() == null) {
            throw new RuntimeException("No profile picture found for user with ID: " + id);
        }
    
        user.setProfilePicture(null); 
        userRepository.save(user);
    
        return "Profile picture deleted successfully!";
    }

}
