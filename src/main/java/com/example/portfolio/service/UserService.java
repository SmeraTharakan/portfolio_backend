package com.example.portfolio.service;

import org.springframework.stereotype.Service;

import com.example.portfolio.repository.UserRepository;
import com.example.portfolio.entity.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
}
