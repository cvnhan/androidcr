package com.cvnhan.androidcr.core.ui.fragment;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.view.View;

import com.cvnhan.androidcr.core.ui.view.NCMCViewSwitcher;


public class NCMCFragmentLoading extends NCMCFragmentBase {

    private NCMCViewSwitcher loadingViewSwitcher;

    protected void setupLoading(View mainView, View loadingView) {
        loadingViewSwitcher = new NCMCViewSwitcher();
        loadingViewSwitcher.setMainView(mainView);
        loadingViewSwitcher.setAlterView(loadingView);
    }

    public void setShowLoading(boolean show) {
        loadingViewSwitcher.showMainView(!show);
    }

}
