package com.outfitgen.fit;

public class UserChoices {

    private final String style;
    private final Double budget;
    public  UserChoices(String style, double budget, String location) {
        this.style = style;
        if(budget != null && budget < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        };
        this.budget = budget;
    }

    public String getStyle() {
        return style;
    }
    public Double getBudget() {
        return budget;
    }

    public boolean choseStyle() {
        return style != null;
    }

    public boolean hasBudgetLimit() {
        return budget != null;
    }
}