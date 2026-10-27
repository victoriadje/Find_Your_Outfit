package com.outfitgen.weatherforecast;

public class ClimateConditionsData {
    private final String location;
    private final double temperature;
    private final double humidity;
    private final double windSpeed;
    private final int weatherId;

    public ClimateConditionsData(String location, double temperature, double humidity, double windSpeed, int weatherId) {
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


}

