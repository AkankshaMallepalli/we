package com.example.weatherapp.controller;

import com.example.weatherapp.entity.CustomUser;
import com.example.weatherapp.service.UserService;

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

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CustomUser user) {
        try {
            // Validate email domain
            if (!isValidEmailDomain(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email domain");
            }

            // Attempt to register user
            CustomUser registeredUser = userService.registerUser(user);

            // Optionally, return registered user data in the response body
            return ResponseEntity.ok(registeredUser);
        } catch (DuplicateKeyException e) {
            // Handle duplicate key errors (e.g., username already exists)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        } catch (Exception e) {
            // Log other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed. Please try again later.");
        }
    }

    private boolean isValidEmailDomain(String email) {
        String[] allowedDomains = {"gmail.com", "yahoo.com", "hotmail.com"}; // Add your desired domains here
        String domain = email.substring(email.lastIndexOf("@") + 1);
        for (String allowedDomain : allowedDomains) {
            if (domain.equals(allowedDomain)) {
                return true;
            }
        }
        return false;
    }
}
