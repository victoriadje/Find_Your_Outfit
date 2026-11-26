package com.outfitgen.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
//TODO: figure out if id is needed.

/**
 * A simple entity representing an outfit.
 * Outfits are a list of clothing items.
 */
public class Outfit {

    //private final String id;
    private final List<ClothingItems> items;

    public Outfit( List<ClothingItems> items) {
        //this.id = id;
        this.items = items;
    }

//    public String getId() {
//        return id;
//    }

    public List<ClothingItems> getItems() {
        return items;
    }

    //Gets the cost of the whole outfit.
    public double getTotalCost() {
        double totalCost = 0;
        for(ClothingItems i : items){
            totalCost += i.getPrice();
        }
        BigDecimal roundedTotal = BigDecimal.valueOf(totalCost);
        roundedTotal = roundedTotal.setScale(2, RoundingMode.HALF_UP);
        return roundedTotal.doubleValue();
    }
}