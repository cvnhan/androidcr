package com.cvnhan.androidcr.ui.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nhancao on 6/14/16.
 * A collection of View with class is key
 */
public abstract class ViewSession {

    private static final String TAG = ViewSession.class.getSimpleName();
    private List<Class> viewList;
    private Map<Class, CurrentViewChangeListener> currentViewChangeListenerMap;
    private Class currentView;

    public ViewSession() {
        viewList = new ArrayList<>();
        currentViewChangeListenerMap = new HashMap<>();
        initialization();
    }

    public List<Class> getViewList() {
        return viewList;
    }

    public void setViewList(List<Class> viewList) {
        this.viewList = viewList;
        if (currentView == null && viewList.size() > 0) currentView = viewList.get(0);
    }

    public Class getCurrentView() {
        return currentView;
    }

    public void setCurrentView(Class currentView) {
        CurrentViewChangeListener currentViewChangeListener = currentViewChangeListenerMap.get(currentView);
        if (this.currentView != currentView) {
            this.currentView = currentView;
            if (currentViewChangeListener != null) {
                currentViewChangeListener.update(getCurrentView());
            }
        }
    }

    public int getViewIndex(Class view) {
        return viewList.indexOf(view);
    }

    public abstract void initialization();

    public void updateCurrentViewChangeListener(Class currentView, CurrentViewChangeListener currentViewChangeListener) {
        currentViewChangeListenerMap.put(currentView, currentViewChangeListener);
    }

    public interface CurrentViewChangeListener {
        void update(Class currentView);
    }
}
