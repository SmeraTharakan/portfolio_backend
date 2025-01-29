package com.example.portfolio.controller;

import com.example.portfolio.service.UserService;
import com.example.portfolio.entity.User;
import com.example.portfolio.responseModel.UserResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*") // Adjust origins as needed
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse<User> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public UserResponse<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public UserResponse<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserResponse<User> updateUser(@PathVariable UUID id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public UserResponse<String> deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/{id}/upload-profile")
    public UserResponse<String> uploadProfilePicture(@PathVariable UUID id, @RequestParam("image") MultipartFile file) {
        return userService.uploadProfilePicture(id, file);
    }

    @GetMapping("/{id}/profile")
    public UserResponse<String> getProfilePicture(@PathVariable UUID id) {
        return userService.getProfilePicture(id);
    }

    @DeleteMapping("/{id}/delete-profile")
    public UserResponse<String> deleteProfilePicture(@PathVariable UUID id) {
        return userService.deleteProfilePicture(id);
    }
}
