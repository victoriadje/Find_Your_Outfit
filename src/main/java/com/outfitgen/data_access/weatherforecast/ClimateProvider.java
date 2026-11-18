package com.outfitgen.data_access.weatherforecast;

//Store weather info

public interface ClimateProvider {
    ClimateData getClimateData(String location) throws Exception;
}
