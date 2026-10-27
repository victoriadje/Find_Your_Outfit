package com.outfitgen.fit;

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