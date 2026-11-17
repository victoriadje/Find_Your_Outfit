package com.outfitgen.use_case.recommendOutfit;
import  com.outfitgen.entity.UserChoices;
/**
 * The Input Data for the Recommend Outfit Use Case.
 */
public class RecommendOutfitInputData {

    private final UserChoices userChoices;
    private final String preferredStyle;
    private final double budget;
    private final double temperature;
    public RecommendOutfitInputData(UserChoices userChoices, String preferredStyle, float budget, float temperature) {
        this.userChoices = userChoices;
        this.preferredStyle = preferredStyle;
        this.budget = budget;
        this.temperature = temperature;
    }

    public UserChoices getUserChoices() {
        return userChoices;
    }

    public String getStyle() {
        return preferredStyle;
    }

    public double getBudget() {
        return budget;
    }

    public double getTemperature() {
        return temperature;
    }
}
