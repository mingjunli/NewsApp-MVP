package com.mingjun.news.di.module;

import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.remote.NewsDataSource;
import com.mingjun.news.data.remote.RetrofitBuilder;
import com.mingjun.news.data.remote.service.NewsService;
import com.mingjun.news.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mingjun on 16/7/7.
 */
@Module
public class NewsModule {

    @Provides
    @PerActivity
    NewsRepository provideNewsRepo(NewsDataSource dataSource) {
        return dataSource;
    }


    @Provides
    @PerActivity
    NewsService providerNewsService() {
        return RetrofitBuilder.build().create(NewsService.class);
    }
}
