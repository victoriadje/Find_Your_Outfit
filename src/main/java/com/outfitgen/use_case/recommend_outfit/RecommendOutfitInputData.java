package com.outfitgen.use_case.recommend_outfit;

/**
 * The Input Data for the Recommend Outfit Use Case.
 */

//TODO: decide if userChoices is needed
public class RecommendOutfitInputData {

    //private final UserChoices userChoices;
    private final String preferredStyle;
    private final Double budget;
    private final double temperature;
    public RecommendOutfitInputData( String preferredStyle, Double budget, double temperature) {
       // this.userChoices = userChoices;
        this.preferredStyle = preferredStyle;
        this.budget = budget;
        this.temperature = temperature;
    }

//    public UserChoices getUserChoices() {
//        return userChoices;
//    }

    public String getStyle() {
        return preferredStyle;
    }

    public Double getBudget() {
        return budget;
    }

    public double getTemperature() {
        return temperature;
    }
}
