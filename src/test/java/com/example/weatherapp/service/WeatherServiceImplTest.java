package com.example.weatherapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class WeatherServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    private WeatherServiceImpl weatherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        String weatherData = "{\"location\":{\"name\":\"New York\",\"region\":\"New York\",\"country\":\"United States of America\"},\"current\":{\"temp_c\":10.0}}";
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn(weatherData);
        String apiKey = "testKey"; // Provide your test API key here
        weatherService = new WeatherServiceImpl(apiKey, restTemplate);
    }

    @Test
    public void testGetWeatherDetails() {
        // Test method implementation
    }
}
