package com.outfitgen.data_access;

import com.outfitgen.entity.ClothingItems;

import java.util.List;
//TODO: Should it be deleted?
public interface ClothesRepo {
    List<ClothingItems> getAllClothingItems();
    ClothingItems getClothingItemById(String id);
    List<ClothingItems> getClothingItemByType(String type);
    void addClothingItem(ClothingItems item);
    void updateClothingItem(ClothingItems updatedItem);
    void deleteClothingItem(String id);
}
