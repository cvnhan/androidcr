package com.cvnhan.androidcr.dagger.module;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Application;

import com.cvnhan.androidcr.utils.ImageLoader;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleUtils {

    @Singleton
    @Provides
    public ImageLoader provideImageLoader(Application application) {
        final Picasso picasso = Picasso.with(application);
        return new ImageLoader(picasso);
    }

    @Singleton
    @Provides
    public Bus provideBus() {
        return new Bus();
    }
}
