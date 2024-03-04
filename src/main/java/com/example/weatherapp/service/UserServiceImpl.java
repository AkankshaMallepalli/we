package com.example.weatherapp.service;

import com.example.weatherapp.entity.CustomUser;
import com.example.weatherapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUser registerUser(CustomUser user) {
        // Logic to register a new user
        return userRepository.save(user);
    }

    @Override
    public CustomUser loginUser(String username, String password) {
        // Logic to authenticate user login
        // Example: Authenticate user based on username and password
        CustomUser user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Return null if authentication fails
    }

    @Override
    public CustomUser getUserById(Long id) {
        // Logic to retrieve user by ID from the database
        return userRepository.findById(id).orElse(null);
    }
}
