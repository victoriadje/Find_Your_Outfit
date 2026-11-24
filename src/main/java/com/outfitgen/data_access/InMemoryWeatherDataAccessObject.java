package com.outfitgen.data_access;

import com.outfitgen.entity.WeatherData;
import com.outfitgen.use_case.recommend_outfit.WeatherDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryWeatherDataAccessObject  implements WeatherDataAccessInterface {
    private final Map<String, Double> weatherMap = new HashMap<>();

    public void addWeather(String location, double temperature) {
        weatherMap.put(location, temperature);

    }
    @Override
    public WeatherData getWeather(String location) {
        if (!weatherMap.containsKey(location)) {
            throw new IllegalArgumentException("Location not found.");
        }
        return new WeatherData(location, weatherMap.get(location));
    }

}
