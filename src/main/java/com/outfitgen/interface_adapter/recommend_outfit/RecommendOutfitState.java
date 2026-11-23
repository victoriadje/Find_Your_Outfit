package com.outfitgen.interface_adapter.recommend_outfit;

import com.outfitgen.entity.Outfit;

import java.util.ArrayList;
import java.util.List;

public class RecommendOutfitState {
    private List<Outfit> recommendedOutfits = new ArrayList<Outfit>();
    private String error = "";
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
