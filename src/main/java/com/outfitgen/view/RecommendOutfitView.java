package com.outfitgen.view;

import com.outfitgen.entity.Outfit;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitController;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitState;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecommendOutfitView extends JPanel implements PropertyChangeListener {
    private final RecommendOutfitViewModel viewModel;
    private final RecommendOutfitController controller;

    //Styles are going to be "pre chosen", they have options, rather then letting users write it out
    private final JComboBox<String> styleInput = new JComboBox<>(new String[]{ "N/A", "Casual", "Formal", "Sporty", "Streetwear"});
    private final JTextField budgetInput = new JTextField(20);
    private final JTextField locationInput = new JTextField(20);

    //TODO: Update how recommendations are outputed
    private final JTextArea outfitResults = new JTextArea(10, 30);

    private final JLabel tempLabel = new JLabel("Weather:");
    private final JLabel errorLabel = new JLabel("Error:");

    private final JButton outfitGenerateButton = new JButton("Generate Outfit Recommendations");

    public RecommendOutfitView(RecommendOutfitController controller, RecommendOutfitViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        viewModel.addPropertyChangeListener(this);
        //TODO: check  options
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Find Your Outfit");
        JLabel subtitleLabel = new JLabel("Find  outfits based on your style, budget, and weather.");
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(styleInput);
        inputPanel.add(budgetInput);
        inputPanel.add(locationInput);
        inputPanel.add(outfitGenerateButton);
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        outputPanel.add(tempLabel);
        outputPanel.add(errorLabel);
        outfitGenerateButton.addActionListener(event -> {
            recommendOutift();
        });


    }

    public void recommendOutift() {
        String style = (String) styleInput.getSelectedItem();
        String budgetString = budgetInput.getText().trim();
        String location = locationInput.getText().trim();

        //Style
        if ("N/A".equals(style)) {
            style = null;
        }

        //Budget
        Double budget = null;
        try {
            if(!budgetString.isBlank()) {
                budget = Double.parseDouble(budgetString);
                if(budget <= 0 ) {
                    throw new IllegalArgumentException("Budget must be a positive number");
                } else if(!Double.isFinite(budget)){
                    throw new IllegalArgumentException("Budget must be a valid finite number");
                }
            }
        } catch (NumberFormatException e) {
            errorLabel.setText("Please enter a valid budget");
            return;
        }

        //Location
        if (location.isBlank()) {
            errorLabel.setText("Please enter a location");
            return;
        }

        RecommendOutfitState state = viewModel.getState();
        state.setLocation(location);
        state.setBudget(budget);
        controller.execute(style, budget, location);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("state".equals(event.getPropertyName())) {
            RecommendOutfitState state = (RecommendOutfitState) event.getNewValue();
            if (state.getError() != null) {
                errorLabel.setText(state.getError());
                tempLabel.setText("");
                outfitResults.setText("");
            } else {
                errorLabel.setText("");
                tempLabel.setText(state.getTemperature()+"C");
                displayRecommendedOutfits(state);
            }
        }
    }

    private void displayRecommendedOutfits(RecommendOutfitState state) {
        StringBuilder recommendedResults = new StringBuilder();
        int outfitNumber = 0;

        for (Outfit outfit : state.getRecommendedOutfits()) {
            outfitNumber++;
            recommendedResults.append("Outfit ");
            recommendedResults.append(outfitNumber);
            recommendedResults.append("\n");

            recommendedResults.append(outfit);
            recommendedResults.append("\n\n\n");
        }
        if (outfitNumber == 0) {
            recommendedResults.append("No Outfit Found");
        }
        outfitResults.setText(recommendedResults.toString());
    }

}
