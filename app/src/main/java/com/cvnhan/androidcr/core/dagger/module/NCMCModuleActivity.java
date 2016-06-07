package com.cvnhan.androidcr.core.dagger.module;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Activity;
import android.view.LayoutInflater;

import com.cvnhan.core.dagger.NCMCIPerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class NCMCModuleActivity {
    private final Activity activity;

    public NCMCModuleActivity(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @NCMCIPerActivity
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @NCMCIPerActivity
    public LayoutInflater provideLayoutInflater() {
        return activity.getLayoutInflater();
    }
}