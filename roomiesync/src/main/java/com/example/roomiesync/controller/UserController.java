package com.example.roomiesync.controller;

import com.example.roomiesync.model.User;
import com.example.roomiesync.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Allow CORS from React frontend
public class UserController {

    public static final Logger log = LoggerFactory.getLogger(ApplicationListener.class);

    @Autowired
    private UserService userService;

    // This is just a basic hardcoded credential for demonstration purposes
    private final String validUsername = "user@gmail.com"; // Example username
    private final String validPassword = "password123"; // Example password

    // User Registration Endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    // User Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        log.info("Attempting login for username: " + user.getUsername());

        // Check for hardcoded credentials for demo purposes
        if (user.getUsername().equals(validUsername) && user.getPassword().equals(validPassword)) {
            log.info("User successfully authenticated (hardcoded check).");

            return ResponseEntity.ok().body("{ \"message\": \"User logged in successfully.\" }");
        }

        // Authenticate using UserService logic
        boolean isAuthenticated = userService.authenticate(user);
        if (isAuthenticated) {
            return ResponseEntity.ok().body("{ \"message\": \"User logged in successfully.\" }");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{ \"message\": \"Invalid username or password\" }");
        }
    }
}
