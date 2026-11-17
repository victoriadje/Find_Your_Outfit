package com.outfitgen.use_case.recommendOutfit;

import com.outfitgen.data_access.ClothesRepo;
import com.outfitgen.entity.ClothingItems;
import com.outfitgen.entity.Outfit;

import java.util.ArrayList;
import java.util.List;

/**
* The Recommend Outfit Interactor
 */

//TODO: Add fail case logic.
public class RecommendOutfitInteractor implements RecommendOutfitInputBoundary{
    private final ClothesRepo clothesRepo;
    private final RecommendOutfitOutputBoundary presenter;
    public RecommendOutfitInteractor(ClothesRepo clothesRepo, RecommendOutfitOutputBoundary presenter) {
        this.clothesRepo=clothesRepo;
        this.presenter=presenter;
    }

    @Override
    public void execute(RecommendOutfitInputData inputData) {
        List<ClothingItems> items = clothesRepo.getAllClothingItems();
        //TODO: figure out categories. For example, should we add shoes? Jackets? etc
        List<ClothingItems> tops = new ArrayList<ClothingItems>();
        List<ClothingItems> bottoms = new ArrayList<>();
        List<ClothingItems> accessories = new ArrayList<>();

        for(ClothingItems item :items) {
            //TODO: Add filters like temp
            boolean styleMatch = item.getStyle().equals(inputData.getStyle());
            if(styleMatch) {
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
            for(ClothingItems bottom :bottoms) {
                for (ClothingItems accessory : accessories) {
                    double cost = top.getPrice() + bottom.getPrice() + accessory.getPrice();
                    if(cost<= inputData.getBudget()){
                        List<ClothingItems> itemsRecommended =  new ArrayList<>();
                        itemsRecommended.add(top);
                        itemsRecommended.add(bottom);
                        itemsRecommended.add(accessory);
                        Outfit recommendedOutfit = new Outfit(itemsRecommended);
                        recommendation.add(recommendedOutfit);
                    }
                }
            }
        }
        RecommendOutfitOutputData outputData = new RecommendOutfitOutputData(recommendation);
        presenter.prepareSuccessView(outputData);
    }
}

