package com.outfitgen.use_case.recommend_outfit;

/**
 * The output boundary for the Recommend Outfit Use Case.
 */

public interface RecommendOutfitOutputBoundary {
    /**
    * Prepares the success view for the Recommend Outfit Use Case.
    * @param outputData the output data
     */
    void prepareSuccessView(RecommendOutfitOutputData outputData);
    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
