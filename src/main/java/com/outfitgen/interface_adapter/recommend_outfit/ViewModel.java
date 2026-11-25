package com.outfitgen.interface_adapter.recommend_outfit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewModel<T> {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private T state;
    public String viewName = "recommend outfit";

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    //getState returns the current State object stored in View Model
    public T getState() {
        return state;
    }
    public void setState(T state) {
        this.state = state;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    // This gets called by the Presenter when it is done updating the ViewModel. (Note that this delegates to a helper method.
    public void firePropertyChanged() {
        this.support.firePropertyChange("state", null, this.state);
    }

    public void firePropertyChanged(String propertyName) {
        this.support.firePropertyChange(propertyName, null, this.state);
    }

}
