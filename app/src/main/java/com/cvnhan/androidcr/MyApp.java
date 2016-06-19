package com.cvnhan.androidcr;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.cvnhan.androidcr.compat.NCMCPlatformImpSpecificFactory;
import com.cvnhan.androidcr.dagger.ComponentSingleton;
import com.cvnhan.androidcr.dagger.DaggerComponentSingleton;
import com.cvnhan.androidcr.dagger.module.ModuleApplication;
import com.cvnhan.androidcr.mvp.model.local.MLMigration;
import com.squareup.otto.Bus;

import java.io.File;

import io.realm.BuildConfig;
import io.realm.RealmConfiguration;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class MyApp extends MultiDexApplication {

    public static final Bus bus = new Bus();
    private ComponentSingleton component;
    private RealmConfiguration config0;

    public static MyApp get(Context context) {
        return (MyApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug()) {
            NCMCPlatformImpSpecificFactory.getStrictMode().enableStrictMode();
        }
        component = DaggerComponentSingleton.builder()
                .moduleApplication(new ModuleApplication(this))
                .build();
        component().inject(this);
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
