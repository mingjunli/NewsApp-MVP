package com.mingjun.news.di.component;

import com.mingjun.news.di.PerActivity;
import com.mingjun.news.di.module.ActivityModule;
import com.mingjun.news.di.module.NewsModule;
import com.mingjun.news.ui.module.news.NewsActivity;
import com.mingjun.news.ui.module.news.NewsDetailActivity;
import com.mingjun.news.ui.module.news.NewsFragment;

import dagger.Component;

/**
 * Created by mingjun on 16/7/7.
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, NewsModule.class})
public interface NewsComponent extends ActivityComponent {

    void inject(NewsActivity newsActivity);

    void inject(NewsFragment newsFragment);

    void inject(NewsDetailActivity newsDetailActivity);
}
