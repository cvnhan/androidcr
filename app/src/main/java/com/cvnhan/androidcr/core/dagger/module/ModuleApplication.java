package com.cvnhan.androidcr.core.dagger.module;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Application;
import android.location.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A module for Android-specific dependencies which require a
 * {@link android.app.Application} to create.
 */
@Module
public class ModuleApplication {
    private Application application;

    public ModuleApplication(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) application.getSystemService(LOCATION_SERVICE);
    }
}
