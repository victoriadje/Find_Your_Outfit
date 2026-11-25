package com.outfitgen.use_case.recommend_outfit;

import com.outfitgen.entity.ClothingItems;
import java.util.List;

public interface RecommendOutfitDataAccessInterface {
    List<ClothingItems> getAllItems();
}
