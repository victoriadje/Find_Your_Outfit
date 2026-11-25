package com.outfitgen.interface_adapter.recommend_outfit;

//TODO: Update view Model
public class RecommendOutfitViewModel extends ViewModel<RecommendOutfitState> {
    public static final String VIEW_NAME = "Recommend Outfit";
    public RecommendOutfitViewModel() {
        super(VIEW_NAME);
        setState(new RecommendOutfitState());
    }
}
