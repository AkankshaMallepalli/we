package com.example.weatherapp.controller;

import com.example.weatherapp.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeatherByZipCode() {
        String zipCode = "12345";
        String expectedWeatherData = "Sunny";
        when(weatherService.getWeatherDetails(zipCode)).thenReturn(expectedWeatherData);

        ResponseEntity<String> responseEntity = weatherController.getWeatherByZipCode(zipCode);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedWeatherData, responseEntity.getBody());
        verify(weatherService, times(1)).getWeatherDetails(zipCode);
    }

    @Test
    void testGetWeatherByZipCode_Exception() {
        String zipCode = "12345";
        when(weatherService.getWeatherDetails(zipCode)).thenThrow(new RuntimeException("Failed to fetch weather data"));

        ResponseEntity<String> responseEntity = weatherController.getWeatherByZipCode(zipCode);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failed to fetch weather data", responseEntity.getBody());
        verify(weatherService, times(1)).getWeatherDetails(zipCode);
    }
}
