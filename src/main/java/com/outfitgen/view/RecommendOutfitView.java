package com.outfitgen.view;

import com.outfitgen.entity.Outfit;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitController;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitState;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class RecommendOutfitView extends JPanel implements PropertyChangeListener {
    private final RecommendOutfitViewModel viewModel;
    private final RecommendOutfitController controller;

    private final JComboBox<String> styleBox;
    private final JTextField budgetField;
    //private final JSpinner temperatureSpinner;
    private final JButton generateButton;

    //private final JPanel resultsPanel;
    //private final JLabel statusLabel;

    public RecommendOutfitView(RecommendOutfitController controller, RecommendOutfitViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        JLabel titleLabel = new JLabel("Find Your Outfit");
        JLabel subtitleLabel = new JLabel("Find  outfits based on your style, budget, and weather.");
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(titleLabel);
        //headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitleLabel);
        //add(headerPanel, BorderLayout.NORTH);
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(
                BorderFactory.createTitledBorder("Your Preferences"));
        styleBox = new JComboBox<>(new String[]{
                "Any Style",
                "Casual",
                "Formal",
                "Sporty",
                "Streetwear"
        });
        budgetField = new JTextField(12);
        generateButton = new JButton("Generate Outfits");

    }
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        RecommendOutfitState state = viewModel.getRecommendOutfitState();
       // resultsPanel.removeAll();
        if (state.getRecommendedOutfits() == null) {
           // statusLabel.setText(state.getError());
        } else {
            List<Outfit> outfits = state.getRecommendedOutfits();
            //TODO
        }
    }

}
