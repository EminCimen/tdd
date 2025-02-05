package com.weather.gateway;

import com.weather.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class WeatherGateway {
    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String baseUrl;

    public WeatherGateway(
            RestTemplate restTemplate,
            @Value("${openweather.api.key}") String apiKey,
            @Value("${openweather.api.url}") String baseUrl
    ) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    public WeatherResponse getWeatherForCity(String city) {
        try {
            String url = String.format("%s?q=%s&appid=%s&units=metric", baseUrl, city, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return new WeatherResponse(0.0, 0.0, 0, null);
        }
    }
} 