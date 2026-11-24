package com.outfitgen.interface_adapter.recommend_outfit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
//TODO: Update view Model
public class RecommendOutfitViewModel {
    private RecommendOutfitState recommendOutfitState = new RecommendOutfitState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public RecommendOutfitState getRecommendOutfitState() {
        return recommendOutfitState;
    }
    public void setRecommendOutfitState(RecommendOutfitState recommendOutfitState) {
        this.recommendOutfitState = recommendOutfitState;
    }
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, recommendOutfitState);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
