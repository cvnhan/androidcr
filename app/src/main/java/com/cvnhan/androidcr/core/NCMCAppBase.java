package com.cvnhan.androidcr.core;

import android.app.Application;

import com.cvnhan.core.compat.NCMCPlatformImpSpecificFactory;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public abstract class NCMCAppBase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (isDebug()) {
            // Enable strictMode for development. This will help to keep project in high quality.
            NCMCPlatformImpSpecificFactory.getStrictMode().enableStrictMode();
        } else {
        }
    }

    protected abstract boolean isDebug();
}
