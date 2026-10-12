package com.hostel.maintenance.service;

import com.hostel.maintenance.model.User;
import com.hostel.maintenance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        String cleanEmail = user.getEmail().trim().toLowerCase();
        if (userRepository.existsByEmail(cleanEmail)) {
            throw new IllegalArgumentException("This email (" + cleanEmail + ") is already registered. Please login instead.");
        }
        user.setEmail(cleanEmail);
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("STUDENT");
        } else {
            user.setRole(user.getRole().toUpperCase());
        }
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password are required");
        }
        String cleanEmail = email.trim().toLowerCase();
        Optional<User> userOpt = userRepository.findByEmail(cleanEmail);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("No account found with this email. Please register first.");
        }
        User user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password. Please try again.");
        }
        return user;
    }
}
