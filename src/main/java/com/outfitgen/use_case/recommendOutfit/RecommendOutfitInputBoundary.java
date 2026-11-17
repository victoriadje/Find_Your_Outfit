package com.outfitgen.use_case.recommendOutfit;
/**
 * Input Boundary for actions which are related to recommending and outfit.
 */
public interface RecommendOutfitInputBoundary {
    /**
     * Executes the recommend outfit use case.
     * @param recommendOutfitInputData the input data
     */
    void execute(RecommendOutfitInputData recommendOutfitInputData);
}


