package com.cvnhan.androidcr.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.mvp.model.local.MLRadio;
import com.cvnhan.androidcr.mvp.presenter.PRadio;
import com.cvnhan.androidcr.mvp.view.VRadio;
import com.cvnhan.androidcr.ui.fragment.FragmentNav;
import com.cvnhan.androidcr.ui.fragment.HomeFragment;
import com.cvnhan.androidcr.ui.fragment.SecondFragment;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class MainActivity extends BaseActivity implements VRadio {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    PRadio flow;
    FragmentNav nav;

    @Override
    protected void onCreated(Bundle savedInstanceState) {
        nav = FragmentNav.create(this, R.id.content);
        if (savedInstanceState == null) {
            nav.showScreen(new HomeFragment(), false);
        }
        flow.onPStart(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependencies() {
        component().inject(this);
    }

    @Override
    public void render(ArrayList<MLRadio> songList) {

    }

    @Subscribe
    public void getNotify(String s) {
        Log.e(TAG, "getNotify: " + s);
        nav.showScreen(new SecondFragment(), true);
    }

    @Override
    public void onBackPressed() {
        nav.navigateBack();
//        super.onBackPressed();
    }
}
