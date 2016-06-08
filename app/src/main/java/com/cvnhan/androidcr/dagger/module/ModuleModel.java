package com.cvnhan.androidcr.dagger.module;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Application;
import android.util.Log;

import com.cvnhan.androidcr.mvp.model.YKMAIModelApi;
import com.cvnhan.androidcr.mvp.model.MRadio;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmObject;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Module
public class ModuleModel {
    public static final String BASE_URL = "http://vannhan.comuv.com";
    private static final String TRUSTED_HOST = "test3.sunnypoint.jp";
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.setHostnameVerifier((hostname, session) -> hostname.contains(TRUSTED_HOST));
            okHttpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    Log.e("response", response.toString());
                    // Do anything with response here

                    return response;
                }
            });
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    @Singleton
    public YKMAIModelApi provideApiModel() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getUnsafeOkHttpClient())
                .build();
        return restAdapter.create(YKMAIModelApi.class);
    }

    @Provides
    @Singleton
    public MRadio provideRadioModel(Application application) {
        return new MRadio(application, provideApiModel());
    }


}
