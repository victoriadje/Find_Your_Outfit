package com.outfitgen.entity;
/**
*  Entity representing clothing items. Items have an id, name, type, style, price, the temperature range it was made for
 *  a store the item is sold in and a link to the online item.
 */
//Stores a clothing item information
public class ClothingItems {
    private final String id;
    private final String name;
    private final String type;
    private final String style;
    private final double price;
    private final float minTempCelcius;
    private final float maxTempCelcius;
    private final String storeName;
    private final String itemLink;
    /**
     * Creates a new Clothing Item with the given non-empty name and non-empty password.
     * @param id the items id
     * @param name the items name
     * @param type the type
     * @param style the style of the item
     * @param price the cost of the item
     * @param minTempCelcius the min temperature an item was made for
     * @param maxTempCelcius the max temperature an item was made for
     * @param storeName the name of the store
     * @param itemLink the link to the item
     */

    //TODO: Should we add an exception if any of the parameters are empty?
    public ClothingItems(String id, String name, String type, String style, double price, float minTempCelcius,
                        float maxTempCelcius, String storeName, String itemLink) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.style = style;
        this.price = price;
        this.minTempCelcius = minTempCelcius;
        this.maxTempCelcius = maxTempCelcius;
        this.storeName = storeName;
        this.itemLink = itemLink;

    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getStyle() {
        return style;
    }
    public double getPrice() {
        return price;
    }
    public float getMinTempCelcius() {
        return minTempCelcius;
    }
    public float getMaxTempCelcius() {
        return maxTempCelcius;
    }
    public String getStoreName() {
        return storeName;
    }
    public String getItemLink() {
        return itemLink;
    }

    public boolean isAppropriateForTemp(int tempCelcius) {
        return tempCelcius >= minTempCelcius && tempCelcius <= maxTempCelcius;
    }
}