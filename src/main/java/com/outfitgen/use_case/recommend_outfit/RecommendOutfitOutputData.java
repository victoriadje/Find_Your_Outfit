package com.outfitgen.use_case.recommend_outfit;

import com.outfitgen.entity.Outfit;

import java.util.List;

/**
 * Output Data for the Recommend Outfit Use Case.
 */

public class RecommendOutfitOutputData {

    private final List<Outfit> recommendedOutfit;
    private final String location;
    private final double temperature;
    public RecommendOutfitOutputData(List<Outfit> recommendedOutfit, String location, double temperature) {
        this.recommendedOutfit = recommendedOutfit;
        this.location = location;
        this.temperature = temperature;
    }

    public List<Outfit> getOutfit() {
        return recommendedOutfit;
    }
    public String getLocation() {return location;}
    public double getTemperature() {return temperature;}
}
