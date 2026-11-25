package com.outfitgen;

import com.outfitgen.entity.WeatherData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherDataTest {
    @Test
    public void testGetLocation() {
        WeatherData weatherData = new WeatherData("Toronto", 10.0);
        assertEquals("Toronto", weatherData.getLocation());
    }
    @Test
    public void testGetTemperature() {
        WeatherData weatherData = new WeatherData("Toronto", 10.0);
        assertEquals(10.0, weatherData.getTemperature(), 0.01);
    }
    @Test
    public void testNegativeTemperature() {
        WeatherData weatherData = new WeatherData("Toronto", -25.9);
        assertEquals(-25.9, weatherData.getTemperature(), 0.01);
    }
    @Test
    public void testNullTemperature() {
        WeatherData weatherData = new WeatherData("Toronto", 0.0);
        assertEquals(0.0, weatherData.getTemperature(), 0.01);
    }
}
