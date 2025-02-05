package com.weather.controller;

import com.weather.model.WeatherRequest;
import com.weather.model.WeatherResponse;
import com.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping
    public ResponseEntity<WeatherResponse> getWeather(@RequestBody WeatherRequest request) {
        WeatherResponse response = weatherService.getWeatherForCity(request.getCity());
        return ResponseEntity.ok(response);
    }
} 