package com.cvnhan.androidcr;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.cvnhan.androidcr.core.compat.NCMCPlatformImpSpecificFactory;
import com.cvnhan.androidcr.core.dagger.module.ModuleApplication;
import com.cvnhan.androidcr.dagger.ComponentSingleton;
import com.cvnhan.androidcr.dagger.DaggerComponentSingleton;
import com.cvnhan.androidcr.mvp.model.local.MLMigration;

import java.io.File;

import io.realm.BuildConfig;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class MyApplication extends MultiDexApplication {
    private ComponentSingleton component;
    private RealmConfiguration config0;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug()) {
            NCMCPlatformImpSpecificFactory.getStrictMode().enableStrictMode();
            if (com.cvnhan.androidcr.BuildConfig.DEBUG) {
                Timber.plant(new Timber.DebugTree());
            }
        }
        component = DaggerComponentSingleton.builder()
                .moduleApplication(new ModuleApplication(this))
                .build();
        new File(getFilesDir(), "default0");
        config0 = new RealmConfiguration.Builder(this)
//                .deleteRealmIfMigrationNeeded()
                .name("default0")
                .schemaVersion(MLMigration.DBVERSION)
                .migration(new MLMigration())
                .build();
    }

    public ComponentSingleton component() {
        return component;
    }

    public RealmConfiguration getConfig0() {
        return config0;
    }

    protected boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
