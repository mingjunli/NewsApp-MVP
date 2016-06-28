package com.mingjun.news.data.remote;

import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.data.remote.rx.BaseNewsResponseFunc1;
import com.mingjun.news.data.remote.service.NewsService;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;

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

    @Override
    public Observable<ArrayList<NewsCategory>> getNewsCategories() {
        return Observable.create(new Observable.OnSubscribe<ArrayList<NewsCategory>>() {
            @Override
            public void call(Subscriber<? super ArrayList<NewsCategory>> subscriber) {
                // for test now
                ArrayList<NewsCategory> categories = new ArrayList<NewsCategory>();
                NewsCategory popular = new NewsCategory("popular", "热点");
                NewsCategory recomm = new NewsCategory("recomm", "推荐");
                NewsCategory sports = new NewsCategory("sports", "体育");
                NewsCategory tech = new NewsCategory("tech", "科技");
                NewsCategory history = new NewsCategory("history", "历史");

                categories.add(popular);
                categories.add(recomm);
                categories.add(sports);
                categories.add(tech);
                categories.add(history);

                subscriber.onNext(categories);
            }
        });
    }
}
