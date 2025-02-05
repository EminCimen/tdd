package com.weather.controller;

import com.weather.model.WeatherResponse;
import com.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void shouldReturnWeatherResponseWhenValidCityProvided() throws Exception {
        // given
        String city = "Istanbul";
        WeatherResponse weatherResponse = new WeatherResponse(20.0, 22.0, 65, "Clear sky");
        when(weatherService.getWeatherForCity(city)).thenReturn(weatherResponse);

        // when & then
        mockMvc.perform(post("/api/weather")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"city\": \"Istanbul\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value(20.0))
                .andExpect(jsonPath("$.feels_like").value(22.0))
                .andExpect(jsonPath("$.humidity").value(65))
                .andExpect(jsonPath("$.description").value("Clear sky"));
    }
} 