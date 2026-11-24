package com.outfitgen.use_case.recommend_outfit;

import com.outfitgen.data_access.InMemoryWeatherDataAccessObject;
import com.outfitgen.entity.ClothingItems;
import com.outfitgen.entity.Outfit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendOutfitInteractorTest {
    private RecommendOutfitInteractor interactor;
    private InMemoryClothingRepo clothingRepo;
    private InMemoryWeatherDataAccessObject weatherDAO;
    private OutputRecorder recorder;

    // This class acts as our presenter for testing.
    // It records the data passed by the interactor.
    private static class OutputRecorder implements RecommendOutfitOutputBoundary {
        RecommendOutfitOutputData success;
        String failure;
        @Override
        public void prepareSuccessView(RecommendOutfitOutputData outputData) {
            success = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            failure = errorMessage;
        }
    }
    private static class InMemoryClothingRepo implements RecommendOutfitDataAccessInterface{
        private final List<ClothingItems> items = new ArrayList<>();
        public void addClothingItem(ClothingItems item){
            items.add(item);
        }
        public void clear() {
            items.clear();
        }
        @Override
        public List<ClothingItems> getAllItems() {
            return new ArrayList<>(items);
        }
    }
    @BeforeEach
    public void setup(){
        clothingRepo = new InMemoryClothingRepo();
        weatherDAO = new InMemoryWeatherDataAccessObject();
        recorder = new OutputRecorder();
        interactor= new RecommendOutfitInteractor(clothingRepo, weatherDAO, recorder);

        clothingRepo.addClothingItem(new ClothingItems(
                "1", "Winter Sweater", "top", "casual", "blue",
                40.0, -30.0, 10.0, "Zara", "", ""
        ));

        clothingRepo.addClothingItem(new ClothingItems(
                "2", "Winter Pants", "bottom", "casual", "white",
                50.0, -30.0, 10.0, "Zara", "", ""
        ));

        clothingRepo.addClothingItem(new ClothingItems(
                "3", "Winter Boots", "shoes", "casual", "black",
                60.0, -30.0, 10.0, "H&M", "", ""
        ));

        // Summer clothing.
        clothingRepo.addClothingItem(new ClothingItems(
                "4", "T-Shirt", "top", "casual", "pink",
                15.0, 20.0, 40.0, "H&M", "", ""
        ));

        clothingRepo.addClothingItem(new ClothingItems(
                "5", "Shorts", "bottom", "casual", "white",
                25.0, 20.0, 40.0, "Zara", "", ""
        ));

        clothingRepo.addClothingItem(new ClothingItems(
                "6", "Sandals", "shoes", "casual", "black",
                20.0, 20.0, 40.0, "H&M", "", ""
        ));

        weatherDAO.addWeather("Toronto", -10.0);
        weatherDAO.addWeather("Sao Paulo", 30.0);
        weatherDAO.addWeather("Buenos Aires", 20.0);

        interactor = new RecommendOutfitInteractor(clothingRepo, weatherDAO, recorder);
    }


    @Test
    void successTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Casual", 100.0, "Toronto");
        interactor.execute(inputData);
        assertNotNull(recorder.success);
        assertNull(recorder.failure);
        assertFalse(recorder.success.getOutfit().isEmpty());
        assertEquals(1, recorder.success.getOutfit().size());
        assertTemperatureSuitable(-10.0);

    }
    @Test
    void styleMismatchTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Formal", 100.0, "Toronto");
        interactor.execute(inputData);
        assertNull(recorder.success);
        assertNotNull(recorder.failure);
        assertEquals("No outfits match your preferences", recorder.failure);
    }
    @Test
    void failureBudgetTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Formal", 100.0, "Toronto");
        interactor.execute(inputData);
        assertNull(recorder.success);
        assertNotNull(recorder.failure);
        assertEquals("No outfits match your preferences", recorder.failure);
    }
    @Test
    void successNoStyleTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData(null, 100.0, "Toronto");
        interactor.execute(inputData);
        assertNotNull(recorder.success);
        assertNull(recorder.failure);
        assertEquals(1, recorder.success.getOutfit().size());
    }
    @Test
    void successNoBudgetTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Casual", null, "Toronto");
        interactor.execute(inputData);
        assertNotNull(recorder.success);
        assertNull(recorder.failure);
        assertEquals(1, recorder.success.getOutfit().size());
    }
    @Test
    void emptyRepositoryTest() {
        clothingRepo.clear();
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Casual", null, "Toronto");
        interactor.execute(inputData);
        assertNull(recorder.success);
        assertNotNull(recorder.failure);
        assertEquals("No outfits match your preferences", recorder.failure);
    }
    @Test
    void negativeBudgetTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Casual", -10.0, "Toronto");
        interactor.execute(inputData);
        assertNull(recorder.success);
        assertNotNull(recorder.failure);
        assertEquals("Budget cannot be negative", recorder.failure);
    }
    @Test
    void testTemperatureBoundary() {
        RecommendOutfitInputData inputData =  new RecommendOutfitInputData("Casual", 100.0, "Buenos Aires");
        interactor.execute(inputData);
        assertNotNull(recorder.success);
        assertNull(recorder.failure);
        assertFalse(recorder.success.getOutfit().isEmpty());
        assertTemperatureSuitable(20.0);
    }
    @Test
    void invalidLocation() {
        RecommendOutfitInputData inputData = new RecommendOutfitInputData("Casual", 100.0, "invalid location");
        interactor.execute(inputData);
        assertNull(recorder.success);
        assertNotNull(recorder.failure);

        assertEquals(
                "Unable to fetch weather data",recorder.failure
        );
    }

    private void assertTemperatureSuitable(double temperature) {
        for (Outfit outfit : recorder.success.getOutfit()) {
            assertFalse(outfit.getItems().isEmpty());
            for(ClothingItems item : outfit.getItems()) {
                assertTrue(temperature >= item.getMinTempCelcius(), item.getName() + " is not warm enough for the weather");
                assertTrue(temperature <= item.getMaxTempCelcius(), item.getName() + " is overly warm for the weather");
            }
        }
    }
    @Test
    void temperatureFilterTest() {

        weatherDAO.addWeather("Toronto", 5.0);

        RecommendOutfitInputData inputData =
                new RecommendOutfitInputData(null, null, "Toronto");

        interactor.execute(inputData);

        assertNotNull(recorder.success);
        assertNull(recorder.failure);

        for (Outfit outfit : recorder.success.getOutfit()) {
            for (ClothingItems item : outfit.getItems()) {

                assertTrue(item.isAppropriateForTemp(5.0));
            }
        }
    }
}
