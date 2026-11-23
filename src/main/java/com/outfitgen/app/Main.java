package com.outfitgen.app;

import com.outfitgen.data_access.CSVClothingRepository;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitDataAccessInterface;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            RecommendOutfitDataAccessInterface repository = new CSVClothingRepository("src/main/java/com/outfitgen/data_access/SavedRepository");
            AppBuilder appBuilder = new AppBuilder(repository);
            JFrame application = appBuilder
                    .addRecommendOutfitUseCase()
                    .addRecommendOutfitView()
                    .build();
            application.setVisible(true);
        });

    }
}