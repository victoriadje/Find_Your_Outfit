package com.outfitgen.use_case.recommend_outfit;

import com.outfitgen.entity.ClothingItems;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RecommendOutfitInteractorTest {
    private RecommendOutfitInteractor interactor;
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
        @Override
        public List<ClothingItems> getAllItems() {
            return new ArrayList<>(items);
        }
    }
    @Test
    void successTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Casual", 100.0, 20);
        InMemoryClothingRepo repo = new InMemoryClothingRepo();
        repo.addClothingItem(new ClothingItems("1", "T-Shirt", "top", "Casual", "White", 20, 10, 30, "Zara", "https", "https"));
        repo.addClothingItem(new ClothingItems("2", "Jeans", "bottom", "Casual", "Blue", 40, 0, 30, "H&M", "https", "https"));
        OutputRecorder recorder = new OutputRecorder();
        RecommendOutfitInputBoundary interactor = new RecommendOutfitInteractor(repo, recorder);
        interactor.execute(inputData);
        assertNotNull(recorder.success);
        assertNull(recorder.failure);
        assertFalse(recorder.success.getOutfit().isEmpty());
        assertEquals(1, recorder.success.getOutfit().size());
    }
    @Test
    void styleMismatchTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Formal", 100.0, 20);
        InMemoryClothingRepo repo = new InMemoryClothingRepo();
        repo.addClothingItem(new ClothingItems("1", "T-Shirt", "top", "Casual", "White", 20, 10, 30, "Zara", "https", "https"));
        repo.addClothingItem(new ClothingItems("2", "Jeans", "bottom", "Casual", "Blue", 40, 0, 30, "H&M", "https", "https"));
        OutputRecorder recorder = new OutputRecorder();
        RecommendOutfitInputBoundary interactor = new RecommendOutfitInteractor(repo, recorder);
        interactor.execute(inputData);
        assertNull(recorder.success);
        assertNotNull(recorder.failure);
        assertEquals("No outfits match your preferences", recorder.failure);
    }
    @Test
    void failureBudgetTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Formal", 100.0, 20);
        InMemoryClothingRepo repo = new InMemoryClothingRepo();
        repo.addClothingItem(new ClothingItems("1", "T-Shirt", "top", "Casual", "White", 20, 10, 30, "Zara", "https", "https"));
        repo.addClothingItem(new ClothingItems("2", "Jeans", "bottom", "Casual", "Blue", 40, 0, 30, "H&M", "https", "https"));
        OutputRecorder recorder = new OutputRecorder();
        RecommendOutfitInputBoundary interactor = new RecommendOutfitInteractor(repo, recorder);
        interactor.execute(inputData);
        assertNull(recorder.success);
        assertNotNull(recorder.failure);
        assertEquals("No outfits match your preferences", recorder.failure);
    }
    @Test
    void successNoStyleTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData(null, 100.0, 20);
        InMemoryClothingRepo repo = new InMemoryClothingRepo();
        repo.addClothingItem(new ClothingItems("1", "T-Shirt", "top", "Casual", "White", 20, 10, 30, "Zara", "https", "https"));
        repo.addClothingItem(new ClothingItems("2", "Jeans", "bottom", "Casual", "Blue", 40, 0, 30, "H&M", "https", "https"));
        OutputRecorder recorder = new OutputRecorder();
        RecommendOutfitInputBoundary interactor = new RecommendOutfitInteractor(repo, recorder);
        interactor.execute(inputData);
        assertNotNull(recorder.success);
        assertNull(recorder.failure);
        assertEquals(1, recorder.success.getOutfit().size());
    }
    @Test
    void successNoBudgetTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Casual", null, 20);
        InMemoryClothingRepo repo = new InMemoryClothingRepo();
        repo.addClothingItem(new ClothingItems("1", "T-Shirt", "top", "Casual", "White", 20, 10, 30, "Zara", "https", "https"));
        repo.addClothingItem(new ClothingItems("2", "Jeans", "bottom", "Casual", "Blue", 40, 0, 30, "H&M", "https", "https"));
        OutputRecorder recorder = new OutputRecorder();
        RecommendOutfitInputBoundary interactor = new RecommendOutfitInteractor(repo, recorder);
        interactor.execute(inputData);
        assertNotNull(recorder.success);
        assertNull(recorder.failure);
        assertEquals(1, recorder.success.getOutfit().size());
    }
    @Test
    void emptyRepositoryTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Casual", null, 20);
        InMemoryClothingRepo repo = new InMemoryClothingRepo();
        OutputRecorder recorder = new OutputRecorder();
        RecommendOutfitInputBoundary interactor = new RecommendOutfitInteractor(repo, recorder);
        interactor.execute(inputData);
        assertNull(recorder.success);
        assertNotNull(recorder.failure);
        assertEquals("No outfits match your preferences", recorder.failure);
    }
    @Test
    void negativeBudgetTest() {
        RecommendOutfitInputData inputData= new RecommendOutfitInputData("Casual", -10.0, 20);
        InMemoryClothingRepo repo = new InMemoryClothingRepo();
        OutputRecorder recorder = new OutputRecorder();
        RecommendOutfitInputBoundary interactor = new RecommendOutfitInteractor(repo, recorder);
        interactor.execute(inputData);
        assertNull(recorder.success);
        assertNotNull(recorder.failure);
        assertEquals("Budget cannot be negative", recorder.failure);
    }
}
