package com.example.weatherapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final String weatherApiKey;
    private static final String WEATHER_API_URL = "http://api.weatherapi.com/v1/current.json?key=%s&q=%s";
    private final RestTemplate restTemplate;

    public WeatherServiceImpl(@Value("${weather.api.key}") String weatherApiKey, RestTemplate restTemplate) {
        this.weatherApiKey = weatherApiKey;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getWeatherDetails(String zipCode) {
        String apiUrl = String.format(WEATHER_API_URL, weatherApiKey, zipCode);
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
