package com.example.roomiesync.service;

import com.example.roomiesync.model.User;
import com.example.roomiesync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register User (no password hashing)
    public void registerUser(User user) {
        // Save the user directly without modifying the password
        userRepository.save(user);
    }

    // Authenticate User (plain text password check)
    public boolean authenticate(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            // Compare the provided password with the stored password
            return user.getPassword().equals(existingUser.get().getPassword());
        }
        return false;
    }
}
