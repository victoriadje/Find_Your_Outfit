package java.com.outfitgen;

import com.outfitgen.data_access.CSVClothingRepository;
import com.outfitgen.entity.ClothingItems;

import java.util.List;

public class TestCSV {
    public  static void main(String[] args) {
        CSVClothingRepository repo = new CSVClothingRepository("src/main/resources/clothings.csv");
        List<ClothingItems> items = repo.getAllItems();
        for (ClothingItems item : items) {
            System.out.println(item.toString());
        }
    }
}
