package com.cvnhan.androidcr.dagger.module;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Activity;
import android.view.LayoutInflater;

import com.cvnhan.androidcr.dagger.IPerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ModuleActivity {
    private final Activity activity;

    public ModuleActivity(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @IPerActivity
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @IPerActivity
    public LayoutInflater provideLayoutInflater() {
        return activity.getLayoutInflater();
    }
}