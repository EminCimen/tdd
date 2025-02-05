package com.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private double temperature;
    private double feelsLike;
    private int humidity;
    private String description;

    // Default constructor
    public WeatherResponse() {
    }

    // All-args constructor
    public WeatherResponse(double temperature, double feelsLike, int humidity, String description) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.description = description;
    }

    @JsonProperty("main")
    private void unpackNested(Main main) {
        this.temperature = main.temp;
        this.feelsLike = main.feels_like;
        this.humidity = main.humidity;
    }

    @JsonProperty("weather")
    private void unpackWeather(Weather[] weather) {
        if (weather != null && weather.length > 0) {
            this.description = weather[0].description;
        }
    }

    // Getters and Setters
    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Main {
        public double temp;
        public double feels_like;
        public int humidity;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Weather {
        public String description;
    }
} 