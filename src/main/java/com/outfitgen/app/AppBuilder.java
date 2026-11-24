package com.outfitgen.app;

import com.outfitgen.data_access.CSVClothingRepository;
import com.outfitgen.data_access.WeatherDataAccessObject;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitController;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitPresenter;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitViewModel;
import com.outfitgen.use_case.recommend_outfit.*;
import com.outfitgen.view.RecommendOutfitView;

import javax.swing.*;
import java.awt.*;

//TODO: whole class
public class AppBuilder {
    private final JPanel recommendOutfitPanel = new JPanel();
    private final CardLayout recommendLayout = new CardLayout();

    private final RecommendOutfitDataAccessInterface clothingDAO;
    private final WeatherDataAccessInterface weatherDAO;

    private RecommendOutfitViewModel recommendOutfitViewModel;
    private RecommendOutfitView recommendOutfitView;
    private RecommendOutfitController recommendController;

    //Initializing the data access objs
    public AppBuilder() {
        recommendOutfitPanel.setLayout(recommendLayout);

        clothingDAO = new CSVClothingRepository("ssrc/main/java/com/outfitgen/data_access/SavedRepository");
        weatherDAO = new WeatherDataAccessObject();
    }
    //Create the view and view Model
    public AppBuilder addRecommendOutfitView(){
        recommendOutfitViewModel = new RecommendOutfitViewModel();

        recommendOutfitView = new RecommendOutfitView(recommendController, recommendOutfitViewModel);

        recommendOutfitPanel.add(recommendOutfitView);
        return this;

    }
    //Connect the recommend outfit use case
    public AppBuilder addRecommendOutfitUseCase(){
        RecommendOutfitOutputBoundary presenter = new RecommendOutfitPresenter(recommendOutfitViewModel);

        RecommendOutfitInputBoundary interactor = new RecommendOutfitInteractor(clothingDAO, weatherDAO, presenter);

        RecommendOutfitController controller = new RecommendOutfitController(interactor);

        return this;
    }

    //Builds the application window
    public JFrame build(){
       JFrame app = new JFrame("Find Your Outfit");
       app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       app.add(recommendOutfitPanel);
       recommendLayout.show(recommendOutfitPanel, "recommendOutfitPanel");
       app.pack();
       return app;
    }

}
