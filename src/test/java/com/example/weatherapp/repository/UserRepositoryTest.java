package com.example.weatherapp.repository;


import com.example.weatherapp.entity.CustomUser;
import com.example.weatherapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository; // Mock the UserRepository interface

    @Mock
    private CustomUser customUser; // Mock the CustomUser class

    @Test
    public void testFindByUsername() {
        // Mock the behavior of the UserRepository's findByUsername method
        when(userRepository.findByUsername(anyString())).thenReturn(customUser);

        // Call the findByUsername method with a dummy username
        CustomUser result = userRepository.findByUsername("dummyUsername");

        // Verify that the result is the same as the mocked CustomUser
        assertEquals(customUser, result);
    }
}
