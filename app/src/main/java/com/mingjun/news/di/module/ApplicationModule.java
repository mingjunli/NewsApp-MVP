package com.mingjun.news.di.module;

import android.app.Application;
import android.content.Context;

import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.remote.NewsDataSource;
import com.mingjun.news.data.remote.RetrofitBuilder;
import com.mingjun.news.data.remote.service.NewsService;
import com.mingjun.news.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

}
