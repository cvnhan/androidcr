package com.cvnhan.androidcr.dagger.module;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Application;

import com.cvnhan.androidcr.mvp.model.YKMAIModelApi;
import com.cvnhan.androidcr.mvp.model.YKMAModelPlayer;
import com.cvnhan.androidcr.mvp.model.YKMAModelRadio;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmObject;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module
public class YKMAModuleModel {
    public static final String BASE_URL = "http://vannhan.comuv.com";

    @Provides
    @Singleton
    public YKMAIModelApi provideApiModel() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(new GsonBuilder()
                        .setExclusionStrategies(new ExclusionStrategy() {
                            @Override
                            public boolean shouldSkipField(FieldAttributes f) {
                                return f.getDeclaringClass().equals(RealmObject.class);
                            }

                            @Override
                            public boolean shouldSkipClass(Class<?> clazz) {
                                return false;
                            }
                        })
                        .create()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return restAdapter.create(YKMAIModelApi.class);
    }

    @Provides
    @Singleton
    public YKMAModelPlayer providePlayerModel(Application application) {
        return new YKMAModelPlayer(application, provideApiModel());
    }

    @Provides
    @Singleton
    public YKMAModelRadio provideRadioModel(Application application) {
        return new YKMAModelRadio(application, provideApiModel());
    }


}
