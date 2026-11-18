package com.outfitgen.data_access.weatherforecast;

public class ClimateData {
    private final String location;
    private final double temperature;
    private final double humidity;
    private final double windSpeed;
    private final int weatherId;

    public ClimateData(String location, double temperature, double humidity, double windSpeed, int weatherId) {
        this.location = location;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.weatherId = weatherId;
    }
    public String getLocation() {
        return location;
    }
    public double getTemperature() {
        return temperature;
    }
    public double getHumidity() {
        return humidity;
    }
    public double getWindSpeed() {
        return windSpeed;
    }
    public int getWeatherId() {
        return weatherId;
    }

    // TODO: ADD WEATHER CONDITIONS BASED ON CODES FROM API
}

