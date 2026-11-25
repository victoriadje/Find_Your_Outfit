package com.outfitgen.interface_adapter.recommend_outfit;

import com.outfitgen.use_case.recommend_outfit.RecommendOutfitInputBoundary;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitInputData;

public class RecommendOutfitController {
    private final RecommendOutfitInputBoundary interactor;

    public RecommendOutfitController(RecommendOutfitInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String preferredStyle, Double budget, String location) {
        RecommendOutfitInputData inputData = new RecommendOutfitInputData(preferredStyle, budget, location);
        interactor.execute(inputData);
    }
}

