package com.outfitgen.use_case.recommend_outfit;

/**
 * The Input Data for the Recommend Outfit Use Case.
 */

public class RecommendOutfitInputData {

    private final String preferredStyle;
    private final Double budget;
    private final String location;
    public RecommendOutfitInputData( String preferredStyle, Double budget, String location) {
        this.preferredStyle = preferredStyle;
        this.budget = budget;
        this.location = location;
    }


    public String getStyle() {
        return preferredStyle;
    }

    public Double getBudget() {
        return budget;
    }

    public String getLocation() {
        return location;
    }
}
