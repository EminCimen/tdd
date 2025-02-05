package com.weather.service;

import com.weather.gateway.WeatherGateway;
import com.weather.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherGateway weatherGateway;

    public WeatherService(WeatherGateway weatherGateway) {
        this.weatherGateway = weatherGateway;
    }

    public WeatherResponse getWeatherForCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("Şehir adı boş olamaz");
        }
        
        return weatherGateway.getWeatherForCity(city.trim());
    }
} 