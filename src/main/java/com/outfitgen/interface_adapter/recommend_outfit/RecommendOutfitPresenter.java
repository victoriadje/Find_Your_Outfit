package com.outfitgen.interface_adapter.recommend_outfit;

import com.outfitgen.use_case.recommend_outfit.RecommendOutfitOutputBoundary;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitOutputData;

import java.util.ArrayList;
//TODO: Update presenter
public class RecommendOutfitPresenter implements RecommendOutfitOutputBoundary {
    private final RecommendOutfitViewModel viewModel;
    public RecommendOutfitPresenter(RecommendOutfitViewModel viewModel) {
        this.viewModel = viewModel;
    }
    @Override
    public void prepareSuccessView(RecommendOutfitOutputData outputData) {
        RecommendOutfitState state = viewModel.getState();

        state.setLocation(outputData.getLocation());
        state.setTemperature(outputData.getTemperature());
        state.setRecommendedOutfits(outputData.getOutfit());
        state.setError("");
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        RecommendOutfitState state = viewModel.getState();
        state.setError(message);
        state.setLocation("");
        state.setTemperature(null);
        state.setRecommendedOutfits(new ArrayList<>());
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

}
