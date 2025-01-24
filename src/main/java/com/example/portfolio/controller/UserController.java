package com.example.portfolio.controller;

import com.example.portfolio.entity.User;
import com.example.portfolio.service.UserService;
import com.example.portfolio.repository.UserRepository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/{id}/upload-profile")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        try {
            User user = userService.getUserById(id);

            user.setProfilePicture(file.getBytes());

            userRepository.save(user);

            return ResponseEntity.ok("Profile picture uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload image: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        byte[] profilePicture = user.getProfilePicture();

        if (profilePicture == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(profilePicture);
    }
}
