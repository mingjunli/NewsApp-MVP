package com.mingjun.news.data.remote;

import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.remote.rx.BaseNewsResponseFunc1;
import com.mingjun.news.data.remote.service.NewsService;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by mingjun on 16/6/28.
 */
@EBean(scope = EBean.Scope.Singleton)
public class NewsRemoteDataSource implements NewsRepository {

    @Bean
    RetrofitBuilder mRetrofitBuilder;

    private NewsService mNewsService;

    @AfterInject
    void initService() {
        mNewsService = mRetrofitBuilder.build().create(NewsService.class);
    }

    @Override
    public Observable<ArrayList<News>> getNewsByCategory(String category, int page) {
        return mNewsService.getNewsByCategory(category, page)
                .flatMap(new BaseNewsResponseFunc1());
    }
}
