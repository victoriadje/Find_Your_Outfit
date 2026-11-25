package com.outfitgen.interface_adapter.recommend_outfit;

import com.outfitgen.entity.Outfit;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendOutfitPresenterTest {
    private RecommendOutfitPresenter presenter;
    private RecommendOutfitViewModel viewModel;

    @BeforeEach
    public void setUp() {
        viewModel = new RecommendOutfitViewModel();
        presenter = new RecommendOutfitPresenter(viewModel);
    }

    @Test
    void testPrepareSuccessView() {
        List<Outfit> outfits = new ArrayList<>();
        String location = "";
        double temperature = 0;
        RecommendOutfitOutputData outputData = new RecommendOutfitOutputData(outfits, location, temperature);
        presenter.prepareSuccessView(outputData);

        RecommendOutfitState state = viewModel.getState();
        assertNotNull(state.getRecommendedOutfits());
        assertTrue(state.getRecommendedOutfits().isEmpty());
        assertEquals("", state.getError());
    }
    @Test
    void testPrepareFailView() {
        presenter.prepareFailView("No outfits match your preferences");
        RecommendOutfitState state = viewModel.getState();
        assertEquals("No outfits match your preferences", state.getError());
        assertTrue(state.getRecommendedOutfits().isEmpty());
    }
}
