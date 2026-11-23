package com.outfitgen.interface_adapter.recommend_outfit;

import com.outfitgen.use_case.recommend_outfit.RecommendOutfitOutputBoundary;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitOutputData;

import java.util.ArrayList;

public class RecommendOutfitPresenter implements RecommendOutfitOutputBoundary {
    private final RecommendOutfitViewModel viewModel;
    public RecommendOutfitPresenter(RecommendOutfitViewModel viewModel) {
        this.viewModel = viewModel;
    }
    @Override
    public void prepareSuccessView(RecommendOutfitOutputData outputData) {
        RecommendOutfitState state = viewModel.getRecommendOutfitState();
        state.setRecommendedOutfits(outputData.getOutfit());
        state.setError("");
        viewModel.setRecommendOutfitState(state);
        viewModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(String message) {
        RecommendOutfitState state = viewModel.getRecommendOutfitState();
        state.setRecommendedOutfits(new ArrayList<>());
        state.setError(message);
        viewModel.setRecommendOutfitState(state);
        viewModel.firePropertyChanged();
    }

}
