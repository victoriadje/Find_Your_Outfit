package com.outfitgen.app;

import javax.swing.*;

//
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            AppBuilder appBuilder = new AppBuilder();
            JFrame application = appBuilder
                    .addRecommendOutfitView()
                    .addRecommendOutfitUseCase()
                    .build();
            application.setVisible(true);
        });

    }
}