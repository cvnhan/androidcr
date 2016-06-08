package com.cvnhan.androidcr.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

import com.cvnhan.androidcr.MyApplication;
import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.core.dagger.module.ModuleActivity;
import com.cvnhan.androidcr.core.ui.ActivityBase;
import com.cvnhan.androidcr.dagger.ComponentUi;
import com.cvnhan.androidcr.dagger.DaggerComponentUi;
import com.cvnhan.androidcr.mvp.model.local.MLRadio;
import com.cvnhan.androidcr.mvp.presenter.PRadio;
import com.cvnhan.androidcr.mvp.view.VRadio;

import java.util.ArrayList;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class MainActivity extends ActivityBase implements VRadio {
    @Inject
    LocationManager locationManager;
    @Inject
    PRadio flow;
    private ComponentUi uiComponent;

    public ComponentUi component() {
        if (uiComponent == null) {
            uiComponent = DaggerComponentUi.builder()
                    .componentSingleton(((MyApplication) getApplication()).component())
                    .moduleActivity(new ModuleActivity(this))
                    .build();
        }
        return uiComponent;
    }

    @Override
    protected void setupViews() {
        flow.onPStart(this);
        Timber.tag("LifeCycles");
        Timber.d("Activity Created");

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
    protected void onReceive(Context context, Intent intent) {

    }

    @Override
    public void render(ArrayList<MLRadio> songList) {

    }
}
