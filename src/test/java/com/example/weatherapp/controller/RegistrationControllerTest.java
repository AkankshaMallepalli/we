package com.example.weatherapp.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.weatherapp.entity.CustomUser;
import com.example.weatherapp.service.UserService;

public class RegistrationControllerTest {

    private RegistrationController registrationController;
    private UserService userServiceMock;

    @Before
    public void setUp() {
        userServiceMock = mock(UserService.class);
        registrationController = new RegistrationController(userServiceMock);
    }

    @Test
    public void testRegisterUser_Success() {
        // Arrange
        CustomUser user = new CustomUser("username", "password", "user@example.com");
        when(userServiceMock.registerUser(user)).thenReturn(user);

        // Act
        ResponseEntity<?> response = registrationController.registerUser(user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testRegisterUser_DuplicateUsername() {
        // Arrange
        CustomUser user = new CustomUser("existingUsername", "password", "user@example.com");
        when(userServiceMock.registerUser(user)).thenThrow(DuplicateKeyException.class);

        // Act
        ResponseEntity<?> response = registrationController.registerUser(user);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username already exists", response.getBody());
    }

    @Test
    public void testRegisterUser_InvalidEmailDomain() {
        // Arrange
        CustomUser user = new CustomUser("username", "password", "user@invalid.com");

        // Act
        ResponseEntity<?> response = registrationController.registerUser(user);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid email domain", response.getBody());
    }

    @Test
    public void testRegisterUser_InternalServerError() {
        // Arrange
        CustomUser user = new CustomUser("username", "password", "user@example.com");
        when(userServiceMock.registerUser(user)).thenThrow(RuntimeException.class);

        // Act
        ResponseEntity<?> response = registrationController.registerUser(user);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Registration failed. Please try again later.", response.getBody());
    }
}
