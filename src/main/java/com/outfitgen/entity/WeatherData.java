package com.outfitgen.entity;

public class WeatherData {
    private final String location;
    private final double temperature;
    public WeatherData(String location, double temperature) {
        this.location = location;
        this.temperature = temperature;
    }
    public String getLocation() {
        return location;
    }
    public double getTemperature() {
        return temperature;
    }
}
