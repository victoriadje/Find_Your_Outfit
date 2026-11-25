package com.outfitgen;

import com.outfitgen.entity.ClothingItems;
import com.outfitgen.entity.Outfit;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutfitTest {

    @Test
    void getTotalOutfitCost() {
        ClothingItems blackZaraTop = new ClothingItems("1000", "Going out Top", "Top", "NightParty", "black", 15.66, 17,
        35, "Zara", "https://store.com", "a");

        ClothingItems blackHMJeans = new ClothingItems("1001", "Black Baggy Jeans", "Bottoms", "NightParty", "white", 30.09, 10,
                30, "H&M", "https://store2.com", "r");

        Outfit newOutfit = new Outfit(Arrays.asList(blackZaraTop, blackHMJeans));
        assertEquals(45.75, newOutfit.getTotalCost(), 0.001);
    }
    @Test
    void getEmptyOutfitCost() {
        Outfit newOutfit = new Outfit(Collections.emptyList());
        assertEquals(0, newOutfit.getTotalCost(), 0.001);
    }
}
