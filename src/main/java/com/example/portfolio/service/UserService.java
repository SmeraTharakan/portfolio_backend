package com.example.portfolio.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.portfolio.repository.UserRepository;
import com.example.portfolio.entity.User;
import com.example.portfolio.responseModel.UserResponse;

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

    public UserResponse<User> getUserById(UUID id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
            return new UserResponse<>(HttpStatus.OK.value(), "Success", "User retrieved successfully", user);
        } catch (RuntimeException e) {
            return new UserResponse<>(HttpStatus.NOT_FOUND.value(), "Error", e.getMessage(), null);
        }
    }

    public UserResponse<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return new UserResponse<>(HttpStatus.OK.value(), "Success", "All users retrieved successfully", users);
        } catch (Exception e) {
            return new UserResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", e.getMessage(), null);
        }
    }

    public UserResponse<User> createUser(User user) {
        try {
            User savedUser = userRepository.save(user);
            return new UserResponse<>(HttpStatus.CREATED.value(), "Success", "User created successfully", savedUser);
        } catch (Exception e) {
            return new UserResponse<>(HttpStatus.BAD_REQUEST.value(), "Error", e.getMessage(), null);
        }
    }

    public UserResponse<User> updateUser(UUID id, User userDetails) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPhone(userDetails.getPhone());
            existingUser.setBio(userDetails.getBio());
            existingUser.setProfilePicture(userDetails.getProfilePicture());
            existingUser.setLocation(userDetails.getLocation());
            existingUser.setLinkedinUrl(userDetails.getLinkedinUrl());
            existingUser.setGithubUrl(userDetails.getGithubUrl());

            User updatedUser = userRepository.save(existingUser);
            return new UserResponse<>(HttpStatus.OK.value(), "Success", "User updated successfully", updatedUser);
        } catch (RuntimeException e) {
            return new UserResponse<>(HttpStatus.NOT_FOUND.value(), "Error", e.getMessage(), null);
        } catch (Exception e) {
            return new UserResponse<>(HttpStatus.BAD_REQUEST.value(), "Error", e.getMessage(), null);
        }
    }

    public UserResponse<String> deleteUser(UUID id) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

            userRepository.delete(existingUser);
            return new UserResponse<>(HttpStatus.OK.value(), "Success", "User deleted successfully", null);
        } catch (RuntimeException e) {
            return new UserResponse<>(HttpStatus.NOT_FOUND.value(), "Error", e.getMessage(), null);
        }
    }

    public UserResponse<String> uploadProfilePicture(UUID id, MultipartFile file) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

            user.setProfilePicture(file.getBytes());
            userRepository.save(user);

            return new UserResponse<>(HttpStatus.OK.value(), "Success", "Profile picture uploaded successfully!", null);
        } catch (IOException e) {
            return new UserResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", "File processing error", null);
        } catch (RuntimeException e) {
            return new UserResponse<>(HttpStatus.NOT_FOUND.value(), "Error", e.getMessage(), null);
        }
    }

    public UserResponse<String> getProfilePicture(UUID id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

            byte[] profilePicture = user.getProfilePicture();
            if (profilePicture == null) {
                return new UserResponse<>(HttpStatus.NOT_FOUND.value(), "Error", "No profile picture found", null);
            }

            String base64Image = Base64.getEncoder().encodeToString(profilePicture);
            return new UserResponse<>(HttpStatus.OK.value(), "Success", "Profile picture retrieved successfully", base64Image);
        } catch (RuntimeException e) {
            return new UserResponse<>(HttpStatus.NOT_FOUND.value(), "Error", e.getMessage(), null);
        }
    }

    public UserResponse<String> deleteProfilePicture(UUID id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

            if (user.getProfilePicture() == null) {
                return new UserResponse<>(HttpStatus.NOT_FOUND.value(), "Error", "No profile picture found", null);
            }

            user.setProfilePicture(null);
            userRepository.save(user);

            return new UserResponse<>(HttpStatus.OK.value(), "Success", "Profile picture deleted successfully!", null);
        } catch (RuntimeException e) {
            return new UserResponse<>(HttpStatus.NOT_FOUND.value(), "Error", e.getMessage(), null);
        }
    }
}
