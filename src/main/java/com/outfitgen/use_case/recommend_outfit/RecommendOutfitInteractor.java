package com.outfitgen.use_case.recommend_outfit;

import com.outfitgen.entity.ClothingItems;
import com.outfitgen.entity.Outfit;

import java.util.ArrayList;
import java.util.List;
/**
* The Recommend Outfit Interactor
 */
public class RecommendOutfitInteractor implements RecommendOutfitInputBoundary{
    private final RecommendOutfitDataAccessInterface clothingDataAccess;
    private final RecommendOutfitOutputBoundary presenter;
    public RecommendOutfitInteractor(RecommendOutfitDataAccessInterface clothingDataAccess, RecommendOutfitOutputBoundary presenter) {
        this.clothingDataAccess=clothingDataAccess;
        this.presenter=presenter;
    }

    @Override
    public void execute(RecommendOutfitInputData inputData) {
        if(inputData.getBudget() != null && inputData.getBudget() < 0) {
            presenter.prepareFailView("Budget cannot be negative");
            return;
        }

        List<ClothingItems> items = clothingDataAccess.getAllItems();

        List<ClothingItems> tops = new ArrayList<ClothingItems>();
        List<ClothingItems> bottoms = new ArrayList<>();
        List<ClothingItems> accessories = new ArrayList<>();

        for(ClothingItems item :items) {
            //TODO: Add filters like temp
            boolean styleMatch = inputData.getStyle() == null || inputData.getStyle(). isBlank() ||
                    item.getStyle().equalsIgnoreCase(inputData.getStyle());
            boolean tempMatch = item.isAppropriateForTemp(inputData.getTemperature());
            if(styleMatch && tempMatch) {
                if(item.getType().equalsIgnoreCase("top")){
                    tops.add(item);
                } else if(item.getType().equalsIgnoreCase("accessories")){
                    accessories.add(item);
                } else if(item.getType().equalsIgnoreCase("bottom")){
                    bottoms.add(item);
                }
            }
        }
        //Create an outfit recommendation
        List<Outfit> recommendation = new ArrayList<>();

        for(ClothingItems top :tops) {
            for (ClothingItems bottom : bottoms) {
                double baseCost = top.getPrice() + bottom.getPrice();
                List<ClothingItems> baseRecommended = new ArrayList<>();
                baseRecommended.add(top);
                baseRecommended.add(bottom);
                if (baseCost <= inputData.getBudget()) {
                    recommendation.add(new Outfit(baseRecommended));
                }
                for (ClothingItems accessory : accessories) {
                    double totalCost = accessory.getPrice() + accessory.getPrice();
                    if (totalCost <= inputData.getBudget()) {
                        List<ClothingItems> outfitItems = new ArrayList<>(baseRecommended);
                        outfitItems.add(accessory);
                        recommendation.add(new Outfit(outfitItems));
                    }
                }
            }
        }
        if(recommendation.isEmpty()) {
            presenter.prepareFailView("No outfits match your preferences");
        } else {
            RecommendOutfitOutputData outputData = new RecommendOutfitOutputData(recommendation);
            presenter.prepareSuccessView(outputData);
        }
    }
}