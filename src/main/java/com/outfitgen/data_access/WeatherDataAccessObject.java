package com.outfitgen.data_access;

import com.outfitgen.entity.WeatherData;
import com.outfitgen.use_case.recommend_outfit.WeatherDataAccessInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class WeatherDataAccessObject implements WeatherDataAccessInterface {

    private static final String GEOCODING_URL = "https://geocoding-api.open-meteo.com/v1/search";
    private static final String WEATHER_URL = "https://api.open-meteo.com/v1/forecast";
    private final HttpClient client;

    public WeatherDataAccessObject() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }
    @Override
    public WeatherData getWeather(String location) {
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        String encodedLocation = URLEncoder.encode(location.trim(), StandardCharsets.UTF_8);
        //TODO
        String geocodingUrl = GEOCODING_URL + "?name=" + encodedLocation + "&count=1"+"&language=en"+"&format=json";
        JSONObject geocodingResponse = fetchJson(geocodingUrl);
        JSONArray results = geocodingResponse.optJSONArray("results");
        if (results == null || results.isEmpty()) {
            throw new IllegalArgumentException("Location not found: " + location);
        }
        JSONObject city = results.getJSONObject(0);
        double latitude = city.getDouble("latitude");
        double longitude = city.getDouble("longitude");
        String cityName = city.getString("name");
        String weatherUrl = WEATHER_URL+"?latitude="+latitude+"&longitude="+longitude+"&current=temperature_2m"+"&temperature_unit=celsius";
        JSONObject weatherResponse = fetchJson(weatherUrl);
        double temperature = weatherResponse.getJSONObject("current").getDouble("temperature_2m");
        return new WeatherData(cityName, temperature);
    }
    private JSONObject fetchJson(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new IllegalStateException("Failed : API returned status : " + response.statusCode());
            }
            return new JSONObject(response.body());
        } catch (IOException e) {
            throw new IllegalStateException("Unable to retrieve weather information", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Weather request interrupted", e);
        }
    }
//    private final String location;
//    private final double temperature;
//    private final double humidity;
//    private final double windSpeed;
//    private final int weatherId;
//
//    public WeatherDataAccessObject(String location, double temperature, double humidity, double windSpeed, int weatherId) {
//        this.location = location;
//        this.temperature = temperature;
//        this.humidity = humidity;
//        this.windSpeed = windSpeed;
//        this.weatherId = weatherId;
//    }
//    public String getLocation() {
//        return location;
//    }
//    public double getTemperature() {
//        return temperature;
//    }
//    public double getHumidity() {
//        return humidity;
//    }
//    public double getWindSpeed() {
//        return windSpeed;
//    }
//    public int getWeatherId() {
//        return weatherId;
//    }

    // TODO: ADD WEATHER CONDITIONS BASED ON CODES FROM API
}

