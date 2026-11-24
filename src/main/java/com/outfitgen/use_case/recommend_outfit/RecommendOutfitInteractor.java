package com.outfitgen.use_case.recommend_outfit;

import com.outfitgen.entity.ClothingItems;
import com.outfitgen.entity.Outfit;
import com.outfitgen.entity.WeatherData;

import java.util.ArrayList;
import java.util.List;
/**
* The Recommend Outfit Interactor
 */
public class RecommendOutfitInteractor implements RecommendOutfitInputBoundary{

    private final RecommendOutfitDataAccessInterface clothingDataAccessObject;
    private final WeatherDataAccessInterface weatherDataAccessObject;
    private final RecommendOutfitOutputBoundary presenter;

    public RecommendOutfitInteractor(RecommendOutfitDataAccessInterface clothingDataAccessObject, WeatherDataAccessInterface weatherDataAccessObject, RecommendOutfitOutputBoundary presenter) {
        this.clothingDataAccessObject=clothingDataAccessObject;
        this.weatherDataAccessObject=weatherDataAccessObject;
        this.presenter=presenter;
    }

    @Override
    public void execute(RecommendOutfitInputData inputData) {

        if(inputData.getBudget() != null && inputData.getBudget() < 0) {
            presenter.prepareFailView("Budget cannot be negative");
            return;
        }

        WeatherData weather;

        try {
            weather = weatherDataAccessObject.getWeather(inputData.getLocation());
        } catch (RuntimeException e) {
            presenter.prepareFailView("Unable to fetch weather data");
            return;
        }

        double temperature = weather.getTemperature();

        List<ClothingItems> items = clothingDataAccessObject.getAllItems();

        List<ClothingItems> tops = new ArrayList<ClothingItems>();
        List<ClothingItems> bottoms = new ArrayList<>();
        List<ClothingItems> accessories = new ArrayList<>();

        for(ClothingItems item :items) {

            boolean styleMatch = inputData.getStyle() == null || inputData.getStyle(). isBlank() ||
                    item.getStyle().equalsIgnoreCase(inputData.getStyle());

            boolean tempMatch = item.isAppropriateForTemp(temperature);

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
                if (inputData.getBudget()==null || baseCost <= inputData.getBudget()) {
                    recommendation.add(new Outfit(baseRecommended));
                }
                for (ClothingItems accessory : accessories) {
                    double totalCost = baseCost + accessory.getPrice();
                    if (inputData.getBudget()==null || totalCost <= inputData.getBudget()) {
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