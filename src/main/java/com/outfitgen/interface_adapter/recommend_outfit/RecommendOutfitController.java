package com.outfitgen.interface_adapter.recommend_outfit;

import com.outfitgen.use_case.recommend_outfit.RecommendOutfitInputBoundary;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitInputData;

public class RecommendOutfitController {
    private final RecommendOutfitInputBoundary interactor;

    public RecommendOutfitController(RecommendOutfitInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String preferredStyle, Double budget, double temp) {
        RecommendOutfitInputData inputData = new RecommendOutfitInputData(preferredStyle, budget, temp);
        interactor.execute(inputData);
    }
}

