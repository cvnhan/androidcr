package com.cvnhan.androidcr.ui.fragment.tab1;

import android.view.View;

import com.cvnhan.androidcr.ui.view.ViewFlow;
import com.cvnhan.androidcr.ui.view.ViewSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhancao on 6/14/16.
 */
public class Tab1Session extends ViewSession {
    private static final String TAG = Tab1Session.class.getSimpleName();
    private static Tab1Session instance = new Tab1Session();

    public static Tab1Session getInstance() {
        return instance;
    }

    private Tab1Session() {
        super();
    }

    @Override
    public void initialization() {
        List<Class> viewLists = new ArrayList<>();
        viewLists.add(Tab1HomeScreen.class);
        viewLists.add(Tab1NextScreen.class);
        setViewList(viewLists);
    }

    public boolean isHomeScreen() {
        return getCurrentView() == Tab1HomeScreen.class;
    }

    public static void showScreen(ViewFlow viewFlow, View screen) {
        viewFlow.showScreen(screen);
        Tab1Session.getInstance().setCurrentView(screen.getClass());
    }

    public static void goBack(ViewFlow viewFlow) {
        viewFlow.goBack();
        Tab1Session.getInstance().setCurrentView(viewFlow.getViewTop().getClass());
    }

}
