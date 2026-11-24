package com.outfitgen.entity;
/**
 * A simple entity representing an user's choices/preferences.
 * Users can have a preferred style and budget
 */
public class UserChoices {

    private final String userName;
    private final String size;
    private final String location;
    private final String style;
    private final Double budget;

    /**
     * Creates user preferences with the given non-empty budget and style (if style is empty, then any style serves).
     * @param style the user's style
     * @param budget the user's budget
     * @throws IllegalArgumentException if the budget is negative
     */
    //TODO: update once location once meteorological part of the code is done.
    public  UserChoices(String style, double budget, String location) {
        this.size = size;
        this.budget = budget;
        this.location = location;
        this.style = style;

        if(budget < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        }
        this.budget = budget;
    }

    public String getStyle() {
        return style;
    }
    public String getSize() { return size; }
    public String getLocation() { return location}
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