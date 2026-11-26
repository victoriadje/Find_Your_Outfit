package com.outfitgen.view;

import com.outfitgen.entity.Outfit;
import com.outfitgen.interface_adapter.logout.LogoutController;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitController;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitState;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecommendOutfitView extends JPanel implements PropertyChangeListener {
    private final RecommendOutfitViewModel viewModel;
    private final RecommendOutfitController controller;
    private LogoutController logoutController;

    //Styles are going to be "pre chosen", they have options, rather than letting users write it out
    private final JComboBox<String> styleInput = new JComboBox<>(new String[]{ "N/A", "Casual", "Formal", "Sporty", "Streetwear"});
    private final JTextField budgetInput = new JTextField(20);
    private final JTextField locationInput = new JTextField(20);

    //TODO: Update how recommendations are outputted
    private final JTextArea outfitResults = new JTextArea(10, 30);

    private final JLabel tempLabel = new JLabel("Weather:");
    private final JLabel errorLabel = new JLabel("");

    public RecommendOutfitView(RecommendOutfitController controller, RecommendOutfitViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        viewModel.addPropertyChangeListener(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel titleLabel = new JLabel("Find Your Outfit");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        JLabel subtitleLabel = new JLabel("Find  outfits based on your style, budget, and weather.");
        subtitleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

//
//        JPanel headerPanel = new JPanel();
//        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
//        headerPanel.add(titleLabel);
//        headerPanel.add(subtitleLabel);
        add(titleLabel);
        add(Box.createVerticalStrut(5));
        add(subtitleLabel);
        add(Box.createVerticalStrut(20));


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        inputPanel.add(new JLabel("Preferred Style:"));
        inputPanel.add(styleInput);

        inputPanel.add(new JLabel("Budget:"));
        inputPanel.add(budgetInput);

        inputPanel.add(new JLabel("Location:"));
        inputPanel.add(locationInput);

        JButton outfitGenerateButton = new JButton("Generate Outfit Recommendations");
        inputPanel.add(outfitGenerateButton);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        outputPanel.add(tempLabel);
        outputPanel.add(errorLabel);

        outfitGenerateButton.addActionListener(event -> {
            recommendOutfit();
        });
        // Log Out button: saves the user's changes and goes back to the login screen.
        JButton logOutButton = new JButton("Log out");

        logOutButton.addActionListener(event -> {
            logoutController.execute();
        });

        outfitResults.setEditable(false);
        outputPanel.add(new JScrollPane(outfitResults));
        //add(headerPanel);
        add(inputPanel);
        add(outputPanel);
        add(logOutButton);

    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void recommendOutfit() {
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

        controller.execute(style, budget, location);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("state".equals(event.getPropertyName())) {
            RecommendOutfitState state = (RecommendOutfitState) event.getNewValue();
            if (state.getError() != null && !state.getError().isBlank()) {
                errorLabel.setText("Error: " + state.getError());
                tempLabel.setText("Weather: Not available");
                outfitResults.setText("");
            } else {
                errorLabel.setText("");
                tempLabel.setText("Current temperature: " + state.getTemperature()+"C");
                displayRecommendedOutfits(state);
            }
        }
    }

    private void displayRecommendedOutfits(RecommendOutfitState state) {
        StringBuilder recommendedResults = new StringBuilder();
        int outfitNumber = 0;

        if (state.getRecommendedOutfits() == null || state.getRecommendedOutfits().isEmpty()) {
            recommendedResults.append("No Outfit Found");

        }
        for (Outfit outfit : state.getRecommendedOutfits()) {
            outfitNumber++;
            recommendedResults.append("Outfit ");
            recommendedResults.append(outfitNumber);
            recommendedResults.append("\n");

            for(var item : outfit.getItems()) {
                recommendedResults.append(item.getName());
                recommendedResults.append("-- $");
                recommendedResults.append(item.getPrice());
                recommendedResults.append("\n");
            }
            recommendedResults.append("Total Price: $");
            recommendedResults.append(outfit.getTotalCost());
            //recommendedResults.append(outfit);
            recommendedResults.append("\n\n");
        }

        outfitResults.setText(recommendedResults.toString());
        outfitResults.setCaretPosition(0);

    }

}

