package com.outfitgen.app;

import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitController;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitPresenter;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitViewModel;
import com.outfitgen.use_case.recommend_outfit.*;
import com.outfitgen.view.RecommendOutfitView;

import javax.swing.*;

//TODO: whole class
public class AppBuilder {
    private final RecommendOutfitDataAccessInterface clothingRepository;
    private final WeatherDataAccessInterface weatherRepository;

    private RecommendOutfitViewModel recommendOutfitViewModel;
    private RecommendOutfitController recommendOutfitController;
    private RecommendOutfitView recommendOutfitView;
    public AppBuilder(RecommendOutfitDataAccessInterface clothingRepository,  WeatherDataAccessInterface weatherRepository) {
        this.clothingRepository = clothingRepository;
        this.weatherRepository = weatherRepository;
    }
    public AppBuilder addRecommendOutfitUseCase(){
        recommendOutfitViewModel = new RecommendOutfitViewModel();
        RecommendOutfitOutputBoundary presenter = new RecommendOutfitPresenter(recommendOutfitViewModel);
        RecommendOutfitInputBoundary interactor = new RecommendOutfitInteractor(clothingRepository, weatherRepository, presenter);
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
