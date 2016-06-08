package com.cvnhan.androidcr.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.cvnhan.androidcr.NCMCCoreApp;
import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.core.dagger.module.NCMCModuleActivity;
import com.cvnhan.androidcr.core.ui.activity.NCMCActivityBase;
import com.cvnhan.androidcr.dagger.DaggerYKMAIComponentUi;
import com.cvnhan.androidcr.dagger.YKMAIComponentUi;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class YKMAMainActivity extends NCMCActivityBase {
    private YKMAIComponentUi uiComponent;

    @Override
    protected void setupViews() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependencies() {
        uiComponent = DaggerYKMAIComponentUi.builder()
                .yKMAIComponentSingleton(((NCMCCoreApp) getApplication()).component())
                .nCMCModuleActivity(new NCMCModuleActivity(this))
                .build();
        uiComponent.inject(this);
    }

    @Override
    protected void onReceive(Context context, Intent intent) {

    }

    public YKMAIComponentUi getUiComponent() {
        return uiComponent;
    }
}
