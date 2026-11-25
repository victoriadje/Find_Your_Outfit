package com.outfitgen.app;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(()-> {

            AppBuilder appBuilder = new AppBuilder();

            JFrame application = appBuilder
                    .addLoginView()
                    .addSignupView()
                    .addLoggedInView()
                    .addRecommendOutfitView()
                    .addSignupUseCase()
                    .addLoginUseCase()
                    .addLogoutUseCase()
                    .addRecommendOutfitUseCase()
                    .build();

            application.pack();
            application.setLocationRelativeTo(null);
            application.setVisible(true);
        });
    }
}