package com.mingjun.news.di.component;

import android.app.Activity;

import com.mingjun.news.di.PerActivity;
import com.mingjun.news.di.module.ActivityModule;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
