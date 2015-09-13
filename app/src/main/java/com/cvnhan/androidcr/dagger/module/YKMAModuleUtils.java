package com.cvnhan.androidcr.dagger.module;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Application;

import com.cvnhan.androidcr.utils.YKMAIUtilImageLoader;
import com.cvnhan.androidcr.utils.YKMAImpUtilImageLoader;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class YKMAModuleUtils {

    @Singleton
    @Provides
    public YKMAIUtilImageLoader provideImageLoader(Application application) {
        final Picasso picasso = Picasso.with(application);
        return new YKMAImpUtilImageLoader(picasso);
    }

    @Singleton
    @Provides
    public Bus provideBus() {
        return new Bus();
    }
}
