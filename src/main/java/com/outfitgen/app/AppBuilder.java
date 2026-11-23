package com.outfitgen.app;

import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitController;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitPresenter;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitViewModel;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitDataAccessInterface;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitInputBoundary;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitInteractor;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitOutputBoundary;
import com.outfitgen.view.RecommendOutfitView;

import javax.swing.*;

//TODO: whole class
public class AppBuilder {
    private final RecommendOutfitDataAccessInterface clothingRepository;

    private RecommendOutfitViewModel recommendOutfitViewModel;
    private RecommendOutfitController recommendOutfitController;
    private RecommendOutfitView recommendOutfitView;
    public AppBuilder(RecommendOutfitDataAccessInterface clothingRepository) {
        this.clothingRepository = clothingRepository;
    }
    public AppBuilder addRecommendOutfitUseCase(){
        recommendOutfitViewModel = new RecommendOutfitViewModel();
        RecommendOutfitOutputBoundary presenter = new RecommendOutfitPresenter(recommendOutfitViewModel);
        RecommendOutfitInputBoundary interactor = new RecommendOutfitInteractor(clothingRepository, presenter);
        recommendOutfitController = new RecommendOutfitController(interactor);
        return this;
    }
    public AppBuilder addRecommendOutfitView(){
        if (recommendOutfitController == null){
            throw new NullPointerException("RecommendOutfitController is null");
        }
        recommendOutfitView = new RecommendOutfitView(recommendOutfitController, recommendOutfitViewModel);
        return this;
    }
    public JFrame build(){
        if (recommendOutfitView == null){
            throw new NullPointerException("RecommendOutfitView is null");
        }
        JFrame frame = new JFrame("Recommend Outfit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(recommendOutfitView);
        return frame;
    }

}
