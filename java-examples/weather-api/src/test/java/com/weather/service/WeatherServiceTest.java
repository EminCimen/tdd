package com.weather.service;

import com.weather.gateway.WeatherGateway;
import com.weather.model.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {
    @Mock
    private WeatherGateway weatherGateway;

    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherService = new WeatherService(weatherGateway);
    }

    @Test
    void shouldReturnWeatherResponseWhenCityIsValid() {
        // given
        String city = "Istanbul";
        WeatherResponse expectedResponse = new WeatherResponse(20.0, 22.0, 65, "Clear sky");
        when(weatherGateway.getWeatherForCity(city)).thenReturn(expectedResponse);

        // when
        WeatherResponse actualResponse = weatherService.getWeatherForCity(city);

        // then
        assertEquals(expectedResponse, actualResponse);
        verify(weatherGateway).getWeatherForCity(city);
    }

    @Test
    void shouldHandleEmptyResponse() {
        // given
        String city = "InvalidCity";
        WeatherResponse emptyResponse = new WeatherResponse(0.0, 0.0, 0, null);
        when(weatherGateway.getWeatherForCity(city)).thenReturn(emptyResponse);

        // when
        WeatherResponse actualResponse = weatherService.getWeatherForCity(city);

        // then
        assertNotNull(actualResponse);
        assertEquals(0.0, actualResponse.getTemperature());
        assertEquals(0.0, actualResponse.getFeelsLike());
        assertEquals(0, actualResponse.getHumidity());
        assertNull(actualResponse.getDescription());
        verify(weatherGateway).getWeatherForCity(city);
    }

    @Test
    void shouldHandleInvalidCity() {
        // given
        String invalidCity = "";
        
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            weatherService.getWeatherForCity(invalidCity);
        });
        verify(weatherGateway, never()).getWeatherForCity(invalidCity);
    }
} 