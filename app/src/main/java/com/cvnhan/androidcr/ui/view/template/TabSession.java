package com.cvnhan.androidcr.ui.view.template;

import android.view.View;

import com.cvnhan.androidcr.ui.fragment.tab1.Tab1HomeScreen;
import com.cvnhan.androidcr.ui.fragment.tab1.Tab1NextScreen;
import com.cvnhan.androidcr.ui.view.ViewFlow;
import com.cvnhan.androidcr.ui.view.ViewSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhancao on 6/14/16.
 */
public class TabSession extends ViewSession {
    private static final String TAG = TabSession.class.getSimpleName();
    private static TabSession instance = new TabSession();

    private TabSession() {
        super();
    }

    public static TabSession getInstance() {
        return instance;
    }

    public static void showScreen(ViewFlow viewFlow, View screen) {
        viewFlow.showScreen(screen);
        TabSession.getInstance().setCurrentView(screen.getClass());
    }

    public static void replaceScreenTop(ViewFlow viewFlow, View screen) {
        viewFlow.replaceScreenTop(screen);
        TabSession.getInstance().setCurrentView(screen.getClass());
    }

    public static void goBack(ViewFlow viewFlow) {
        viewFlow.goBack();
        TabSession.getInstance().setCurrentView(viewFlow.getViewTop().getClass());
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

}
