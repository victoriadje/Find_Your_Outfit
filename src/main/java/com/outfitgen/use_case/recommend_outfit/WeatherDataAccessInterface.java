package com.outfitgen.use_case.recommend_outfit;

import com.outfitgen.entity.WeatherData;

public interface WeatherDataAccessInterface {
//Store weather info
 WeatherData getWeather(String location);
}

