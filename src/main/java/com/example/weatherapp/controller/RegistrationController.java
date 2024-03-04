package com.example.weatherapp.controller;

import com.example.weatherapp.entity.CustomUser;
import com.example.weatherapp.service.UserService;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Import LoggerFactory for getting a logger instance
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class RegistrationController {

    @Autowired
    private UserService userService;

    // Get a logger instance for the RegistrationController class
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CustomUser user) {
        try {
            // Validate user data
            // Example: userService.validateUser(user);

            // Attempt to register user
            CustomUser registeredUser = userService.registerUser(user);

            // Optionally, return registered user data in the response body
            return ResponseEntity.ok(registeredUser);
        } catch (DuplicateKeyException e) {
            // Handle duplicate key errors (e.g., username already exists)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        } catch (Exception e) {
            // Log other exceptions
            logger.error("Error registering user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed. Please try again later.");
        }
    }
}
