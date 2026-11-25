package com.outfitgen;

import com.outfitgen.data_access.CSVClothingRepository;
import com.outfitgen.entity.ClothingItems;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestCSV {
    @Test
    void testReadCSV() {

        CSVClothingRepository repo = new CSVClothingRepository("src/main/java/com/outfitgen/data_access/SavedRepository");
        List<ClothingItems> items = repo.getAllItems();
        assertNotNull(items);
        assertFalse(items.isEmpty());
        ClothingItems item = items.get(0);
        assertNotNull(item);
        assertNotNull(item.getId());
    }
}
