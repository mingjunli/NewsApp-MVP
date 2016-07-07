package com.mingjun.news.di.component;

import android.app.Application;
import android.content.Context;

import com.mingjun.news.di.ApplicationContext;
import com.mingjun.news.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();
}
