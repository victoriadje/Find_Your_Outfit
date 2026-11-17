package com.outfitgen.weatherforecast;

//Store weather info

import java.net.http.HttpClient;

public class OpenMeteoData {
    private final HttpClient client = HttpClient.newHttpClient();

    public ClimateConditionsData getClimateConditions(String location) {}
}
