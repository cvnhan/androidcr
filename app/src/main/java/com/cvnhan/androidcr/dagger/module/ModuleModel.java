package com.cvnhan.androidcr.dagger.module;
/**
 * Created by nhancao on 08-June-16.
 */

import android.app.Application;
import android.util.Log;

import com.cvnhan.androidcr.mvp.model.MApi;
import com.cvnhan.androidcr.mvp.model.MRadio;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

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
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ModuleModel {
    public static final Boolean VERIFY_NEEDED = false;
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

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Interceptor.Chain chain) throws IOException {
                                    Request original = chain.request();
                                    // Request customization: add request headers
                                    Request.Builder requestBuilder = original.newBuilder()
//                                            .header("Accept", "application/json")
//                                            .header("Authorization", token)
                                            .method(original.method(), original.body());
                                    Request request = requestBuilder.build();
                                    Buffer buffer = new Buffer();
                                    if (request.body() != null)
                                        request.body().writeTo(buffer);
                                    if (buffer.size() == 0) buffer.writeUtf8("nothing in body");
                                    Log.e("Retrofit", String.format("Method: %s - Request to %s with->\nBODY: %s", request.method(), request.url(), buffer.readUtf8()));
                                    long t1 = System.nanoTime();
                                    Response response = chain.proceed(request);
                                    long t2 = System.nanoTime();
                                    String msg = response.body().string();
                                    Log.e("Retrofit", String.format("Response from %s in %.1fms%n",
                                            response.request().url(), (t2 - t1) / 1e6d));
                                    Log.e("Retrofit", "response: " + msg);
                                    return response
                                            .newBuilder()
                                            .body(ResponseBody.create(response.body().contentType(), msg))
                                            .build();
                                }
                            })
                    .build();

            okHttpClient.newBuilder().sslSocketFactory(sslSocketFactory);

            if (VERIFY_NEEDED) {
                okHttpClient.newBuilder().hostnameVerifier((hostname, session) -> hostname.contains(TRUSTED_HOST));
            }
            return okHttpClient;
        } catch (Exception e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            return new OkHttpClient();
        }
    }

    @Provides
    @Singleton
    public MApi provideApiModel() {
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
        return restAdapter.create(MApi.class);
    }

    @Provides
    @Singleton
    public MRadio provideRadioModel(Application application) {
        return new MRadio(application, provideApiModel());
    }


}
