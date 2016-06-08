package com.cvnhan.androidcr.ui.activity;

/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cvnhan.androidcr.BuildConfig;
import com.cvnhan.androidcr.MyApp;
import com.cvnhan.androidcr.background.ServiceBase;
import com.cvnhan.androidcr.dagger.ComponentUi;
import com.cvnhan.androidcr.dagger.DaggerComponentUi;
import com.cvnhan.androidcr.dagger.module.ModuleActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    LocationManager locationManager;
    NCMCActivityReceiverBase myReceiver;

    private ComponentUi uiComponent;

    public ComponentUi component() {
        if (uiComponent == null) {
            uiComponent = DaggerComponentUi.builder()
                    .componentSingleton(((MyApp) getApplication()).component())
                    .moduleActivity(new ModuleActivity(this))
                    .build();
        }
        return uiComponent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.SERVICE_BASE) {
            myReceiver = new NCMCActivityReceiverBase();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ServiceBase.ACTIVITY_RECEIVE);
            registerReceiver(myReceiver, filter);
        }

        injectDependencies();
        final int layoutId = getContentViewId();
        if (layoutId == 0) {
            final View contentView = getContentView();
            if (contentView != null) {
                setContentView(contentView);
            }
        } else {
            setContentView(layoutId);
        }
        ButterKnife.bind(this);
        ((MyApp) getApplication()).getApplicationBus().register(this);

        onCreated(savedInstanceState);
    }

    protected abstract void onCreated(Bundle savedInstanceState);

    protected abstract int getContentViewId();

    protected View getContentView() {
        return null;
    }

    protected abstract void injectDependencies();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
        }
    }

    protected void onReceive(Context context, Intent intent) {
    }

    class NCMCActivityReceiverBase extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            BaseActivity.this.onReceive(context, intent);
        }
    }

}