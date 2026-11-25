package com.outfitgen;

import com.outfitgen.data_access.WeatherDataAccessObject;
import com.outfitgen.entity.WeatherData;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherDataAccessObjectTest {
    @Test
    void successTest() {
        WeatherDataAccessObject weatherDAO = new WeatherDataAccessObject();
        WeatherData weather = weatherDAO.getWeather("Toronto");
        assertNotNull(weather);
        assertEquals("Toronto", weather.getLocation());
        //Double.isFinite checks that weather is a valid number
        assertTrue(Double.isFinite(weather.getTemperature()));
    }
    @Test
    void invalidLocationTest() {
        WeatherDataAccessObject weatherDAO = new WeatherDataAccessObject();
        assertThrows(IllegalArgumentException.class, () -> weatherDAO.getWeather("InvalidLocation"));
    }
}
