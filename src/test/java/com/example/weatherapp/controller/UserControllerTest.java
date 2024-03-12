package com.example.weatherapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.weatherapp.entity.CustomUser;
import com.example.weatherapp.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    private UserController userController;
    private UserService userServiceMock;

    @BeforeEach
    public void setUp() {
        userServiceMock = mock(UserService.class);
        userController = new UserController(userServiceMock);
    }

    @Test
    public void testGetUserById_ValidId() {
        // Arrange
        long userId = 123;
        CustomUser expectedUser = new CustomUser();
        expectedUser.setId(userId);
        expectedUser.setUsername("testUser");

        when(userServiceMock.getUserById(userId)).thenReturn(expectedUser);

        // Act
        ResponseEntity<CustomUser> responseEntity = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUser, responseEntity.getBody());
    }

    @Test
    public void testGetUserById_InvalidId() {
        // Arrange
        long userId = 456;

        when(userServiceMock.getUserById(userId)).thenReturn(null);

        // Act
        ResponseEntity<CustomUser> responseEntity = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }
}
