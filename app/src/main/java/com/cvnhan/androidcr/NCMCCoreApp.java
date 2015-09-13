package com.cvnhan.androidcr;

import android.content.Context;

import com.cvnhan.androidcr.dagger.DaggerYKMAIComponentSingleton;
import com.cvnhan.androidcr.dagger.YKMAIComponentSingleton;
import com.cvnhan.androidcr.dagger.module.YKMAModuleUtils;
import com.cvnhan.androidcr.mvp.model.local.YKMADBMigration;
import com.cvnhan.core.NCMCAppBase;
import com.cvnhan.core.dagger.module.NCMCModuleApplication;

import java.io.File;

import io.realm.BuildConfig;
import io.realm.RealmConfiguration;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class NCMCCoreApp extends NCMCAppBase {
    private YKMAIComponentSingleton component;
    private RealmConfiguration config0;

    public static NCMCCoreApp get(Context context) {
        return (NCMCCoreApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerYKMAIComponentSingleton.builder()
                .nCMCModuleApplication(new NCMCModuleApplication(this))
                .yKMAModuleUtils(new YKMAModuleUtils())
                .build();
        new File(getFilesDir(), "default0");
        config0 = new RealmConfiguration.Builder(this)
                .name("default0")
                .schemaVersion(YKMADBMigration.DBVERSION)
                .migration(new YKMADBMigration())
                .build();
//        Mint.initAndStartSession(this, "cde0ba64");
    }

    public YKMAIComponentSingleton component() {
        return component;
    }

    public RealmConfiguration getConfig0() {
        return config0;
    }

    @Override
    protected boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
