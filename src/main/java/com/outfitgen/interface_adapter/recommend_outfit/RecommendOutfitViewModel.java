package com.outfitgen.interface_adapter.recommend_outfit;

//TODO: Update view Model
public class RecommendOutfitViewModel extends ViewModel<RecommendOutfitState> {
    public static final String TITLE_LABEL = "Recommend Outfit";
    public static final String LOCATION_LABEL = "Enter your location by city";
    public static final String STYLE_LABEL = "Choose your style (Optional)";
    public static final String BUDGET_LABEL = "Choose your budget limits (Optional)";
    public static final String RECOMMEND_BUTTON_LABEL = "Generate Recommendations";
    public RecommendOutfitViewModel() {
        super();
        setState(new RecommendOutfitState());
    }
}
