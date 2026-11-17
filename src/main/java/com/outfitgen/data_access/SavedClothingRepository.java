//created repository instead of paid API

package com.outfitgen.data_access;

import com.outfitgen.entity.ClothingItems;

import java.util.ArrayList;
import java.util.List;

public class SavedClothingRepository implements ClothesRepo{
   private final List<ClothingItems> items = new ArrayList<>();

   public SavedClothingRepository(){
       //TODO: add items to create our own repo
       items.add(new ClothingItems("id", "Goutfit name", "Top", "NightParty", 0, -1,
               35, "StoreName", ""));

   }
   @Override
   public List<ClothingItems> getAllClothingItems() {
        return new ArrayList<>(items);
    }

    @Override
    public ClothingItems getClothingItemById(String id) {
        for (ClothingItems item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public List<ClothingItems> getClothingItemByType(String type) {
        List<ClothingItems> matchingType = new ArrayList<>();
        for (ClothingItems item : items) {
            if (item.getType().equals(type)) {
                matchingType.add(item);
            }
        }
        return matchingType;
    }

    @Override
    public void addClothingItem(ClothingItems item) {
        if (item == null) {
            throw new NullPointerException("Clothing Item is null");
        }
        items.add(item);
    }

    @Override
    public void updateClothingItem(ClothingItems updatedItem) {
        if (updatedItem == null) {
            throw new NullPointerException("Clothing Item is null");
        }
        for (ClothingItems item : items) {
            if (item.getId().equals(updatedItem.getId())) {
                items.set(items.indexOf(item), updatedItem);
                return;
            }
        }
        throw new IllegalArgumentException("Clothing Item not found");
    }

    @Override
    public void deleteClothingItem(String id) {
        for(int i=0; i < items.size(); i++) {
            if(items.get(i).getId().equals(id)) {
                items.remove(i);
                break;
            }
        }
    }
}
