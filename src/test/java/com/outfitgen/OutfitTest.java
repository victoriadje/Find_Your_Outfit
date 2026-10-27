package com.outfitgen;

import com.outfitgen.fit.ClothingItems;
import com.outfitgen.fit.Outfit;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutfitTest {

    @Test
    void getTotalOutfitCost() {
        ClothingItems blackZaraTop = new ClothingItems("1000", "Going out Top", "Top", "NightParty", 15.66, 17,
        35, "Zara", "https://store.com");

        ClothingItems blackHMJeans = new ClothingItems("1001", "Black Baggy Jeans", "Bottoms", "NightParty", 30.09, 10,
                30, "H&M", "https://store2.com");

        Outfit newOutfit = new Outfit("101", Arrays.asList(blackZaraTop, blackHMJeans));
        assertEquals(45.75, newOutfit.getTotalCost(), 0.001);
    }

}
