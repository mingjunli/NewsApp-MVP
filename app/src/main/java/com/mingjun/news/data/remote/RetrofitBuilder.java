package com.mingjun.news.data.remote;

import android.os.Environment;

import com.mingjun.news.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mingjun on 16/6/28.
 */
public class RetrofitBuilder {

    private static final String END_POINT = "http://apis.baidu.com/";
    private static final long TIMEOUT_CONNECT = 30 * 1000;

    private static Retrofit mRetrofit;

    public Retrofit build() {
        if (mRetrofit != null) {
            return mRetrofit;
        }

        File cacheDirectory = new File(Environment.getExternalStorageDirectory().getPath() + "/cic/CacheResponse.tmp");
        int cacheSize = 10 * 1024 * 1024; // 10M
        Cache cache = new Cache(cacheDirectory, cacheSize);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                .cache(cache);

        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
                builder.addHeader("User-Agent", "MJ_Android");
                builder.addHeader("Cache-Control", "public, max-age=" + 60);
                return chain.proceed(builder.build());
            }
        });

        // Add logging interceptor for every HTTP request in debug version.
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.interceptors().add(loggingInterceptor);
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(END_POINT)
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return mRetrofit;
    }
}
