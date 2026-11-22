//created repository instead of paid API
//TODO: Should we change name for InMemoryClothingRepository?
package com.outfitgen.data_access;

import com.outfitgen.entity.ClothingItems;
import com.outfitgen.use_case.recommend_outfit.RecommendOutfitDataAccessInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads the CSV clothing repository and creates ClothingItem objects
 */
public class CSVClothingRepository implements RecommendOutfitDataAccessInterface {

    private final Path csvFilePath;

    public CSVClothingRepository(String csvFilePath) {
        this.csvFilePath = Path.of(csvFilePath);
    }

    @Override
    public List<ClothingItems> getAllItems() {
        List<ClothingItems> clothingItems = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(csvFilePath)) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                String type = data[2];
                String style = data[3];
                String color = data[4];
                double price = Double.parseDouble(data[5]);
                double minTemp = Double.parseDouble(data[6]);
                double maxTemp = Double.parseDouble(data[7]);
                String brand = data[8];
                String url = data[9];
                String image = data[10];

                ClothingItems item = new ClothingItems(id, name, type, style, color, price, minTemp, maxTemp, brand, url, image);

                clothingItems.add(item);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read csv file");
        }
        return clothingItems;
    }
}

//TODO: Delete the following code if we choose to follow the csv format
//   public CSVClothingRepository(){
//       //TODO: add items to create our own repo
//       items.add(new ClothingItems("id", "Goutfit name", "Top", "NightParty", 0, -1,
//               35, "StoreName", ""));
//
//   }
//   @Override
//   public List<ClothingItems> getAllClothingItems() {
//        return new ArrayList<>(items);
//    }
//
//    @Override
//    public ClothingItems getClothingItemById(String id) {
//        for (ClothingItems item : items) {
//            if (item.getId().equals(id)) {
//                return item;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<ClothingItems> getClothingItemByType(String type) {
//        List<ClothingItems> matchingType = new ArrayList<>();
//        for (ClothingItems item : items) {
//            if (item.getType().equals(type)) {
//                matchingType.add(item);
//            }
//        }
//        return matchingType;
//    }
////TODO: figure if we need to add item
//    @Override
//    public void addClothingItem(ClothingItems item) {
//        if (item == null) {
//            throw new NullPointerException("Clothing Item is null");
//        }
//        items.add(item);
//    }
//
//    @Override
//    public void updateClothingItem(ClothingItems updatedItem) {
//        if (updatedItem == null) {
//            throw new NullPointerException("Clothing Item is null");
//        }
//        for (ClothingItems item : items) {
//            if (item.getId().equals(updatedItem.getId())) {
//                items.set(items.indexOf(item), updatedItem);
//                return;
//            }
//        }
//        throw new IllegalArgumentException("Clothing Item not found");
//    }
//
//    @Override
//    public void deleteClothingItem(String id) {
//        for(int i=0; i < items.size(); i++) {
//            if(items.get(i).getId().equals(id)) {
//                items.remove(i);
//                break;
//            }
//        }
//    }

