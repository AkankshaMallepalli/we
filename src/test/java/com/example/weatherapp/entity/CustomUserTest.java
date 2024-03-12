package com.example.weatherapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomUserTest {

    @Test
    public void testCustomUserConstructor() {
        // Create a new CustomUser object using the constructor
        CustomUser user = new CustomUser("username", "password", "email");

        // Verify that the fields are set correctly
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("email", user.getEmail());
    }

    @Test
    public void testCustomUserSettersAndGetters() {
        // Create a new CustomUser object
        CustomUser user = new CustomUser();

        // Set values using setters
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");

        // Verify that the values are set correctly using getters
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("email", user.getEmail());
    }

    // Add more test cases to cover other methods or behaviors of the CustomUser class
}
