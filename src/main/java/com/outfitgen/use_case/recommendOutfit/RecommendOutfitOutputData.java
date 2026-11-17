package com.outfitgen.use_case.recommendOutfit;

import com.outfitgen.entity.Outfit;

import java.util.List;

/**
 * Output Data for the Recommend Outfit Use Case.
 */

public class RecommendOutfitOutputData {

    private final List<Outfit> recommendedOutfit;

    public RecommendOutfitOutputData(List<Outfit> recommendedOutfit) {
        this.recommendedOutfit = recommendedOutfit;
    }

    public List<Outfit> getOutfit() {
        return recommendedOutfit;
    }
}
