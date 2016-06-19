package com.cvnhan.androidcr.ui.activity;

import com.cvnhan.androidcr.ui.fragment.tab1.Tab1Fragment;
import com.cvnhan.androidcr.ui.fragment.tab2.Tab2Fragment;
import com.cvnhan.androidcr.ui.fragment.tab3.Tab3Fragment;
import com.cvnhan.androidcr.ui.view.ViewSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhancao on 6/14/16.
 */
public class MainSession extends ViewSession {
    private static final String TAG = MainSession.class.getSimpleName();
    private static MainSession instance = new MainSession();

    private MainSession() {
        super();
    }

    public static MainSession getInstance() {
        return instance;
    }

    @Override
    public void initialization() {
        List<Class> viewLists = new ArrayList<>();
        viewLists.add(Tab1Fragment.class);
        viewLists.add(Tab2Fragment.class);
        viewLists.add(Tab3Fragment.class);
        setViewList(viewLists);
    }

    public boolean isTab1Fragment() {
        return getCurrentView() == Tab1Fragment.class;
    }

    public boolean isTab2Fragment() {
        return getCurrentView() == Tab2Fragment.class;
    }

    public boolean isTab3Fragment() {
        return getCurrentView() == Tab3Fragment.class;
    }
}
