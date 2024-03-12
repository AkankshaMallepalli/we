package com.example.weatherapp.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.weatherapp.entity.CustomUser;
import com.example.weatherapp.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest {

    private UserService userService;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        // Create a mock UserRepository
        userRepository = mock(UserRepository.class);
        // Create UserServiceImpl instance with the mock UserRepository
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testRegisterUser() {
        // Create a sample user
        CustomUser user = new CustomUser();
        user.setUsername("testUser");
        user.setPassword("password");
        user.setEmail("test@example.com");

        // Mock the behavior of UserRepository.save() method
        when(userRepository.save(user)).thenReturn(user);

        // Call the registerUser method and assert the result
        CustomUser registeredUser = userService.registerUser(user);
        assertEquals("testUser", registeredUser.getUsername());
        assertEquals("password", registeredUser.getPassword());
        assertEquals("test@example.com", registeredUser.getEmail());
    }

    @Test
    public void testLoginUser_ValidCredentials() {
        // Create a sample user
        CustomUser user = new CustomUser();
        user.setUsername("testUser");
        user.setPassword("password");

        // Mock the behavior of UserRepository.findByUsername() method
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        // Call the loginUser method with valid credentials and assert the result
        CustomUser loggedInUser = userService.loginUser("testUser", "password");
        assertEquals("testUser", loggedInUser.getUsername());
        assertEquals("password", loggedInUser.getPassword());
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        // Mock the behavior of UserRepository.findByUsername() method to return null
        when(userRepository.findByUsername("testUser")).thenReturn(null);

        // Call the loginUser method with invalid credentials and assert the result
        CustomUser loggedInUser = userService.loginUser("testUser", "wrongPassword");
        assertEquals(null, loggedInUser);
    }

    @Test
    public void testGetUserById() {
        // Create a sample user
        CustomUser user = new CustomUser();
        user.setId(1L);
        user.setUsername("testUser");

        // Mock the behavior of UserRepository.findById() method
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        // Call the getUserById method and assert the result
        CustomUser foundUser = userService.getUserById(1L);
        assertEquals("testUser", foundUser.getUsername());
    }
}


