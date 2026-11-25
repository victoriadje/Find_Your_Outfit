package com.outfitgen.interface_adapter.recommend_outfit;

import com.outfitgen.entity.Outfit;

import java.util.ArrayList;
import java.util.List;

public class RecommendOutfitState {
    private String location = "";
    private String style = "";
    private Double budget;

    private Double temperature;
    private String error;
    private List<Outfit> recommendedOutfits = new ArrayList<Outfit>();

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }
    public Double getTemperature() {
        return temperature;
    }
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    public List<Outfit> getRecommendedOutfits() {
        return recommendedOutfits;
    }
    public void setRecommendedOutfits(List<Outfit> recommendedOutfits) {
        this.recommendedOutfits = new ArrayList<>(recommendedOutfits);
    }

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
