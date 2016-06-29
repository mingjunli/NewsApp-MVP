package com.mingjun.news.data.remote;

import com.mingjun.news.NewsApplication;
import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.db.DbFactory;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.data.remote.rx.BaseNewsResponseFunc1;
import com.mingjun.news.data.remote.service.NewsService;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsDataSource implements NewsRepository {

    private NewsService mNewsService;
    private static NewsDataSource sInstance;
    private NewsDataSource() {
        mNewsService = RetrofitBuilder.build().create(NewsService.class);
    }

    public static NewsDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new NewsDataSource();
        }

        return sInstance;
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

                // query from db.
                Realm realm = DbFactory.getRealm(NewsApplication.getInstance());
                RealmResults<NewsCategory> result = realm.where(NewsCategory.class).findAll();

                if (result == null || result.size() == 0) {
                    subscriber.onError(new NullPointerException());
                }
                else {
                    ArrayList<NewsCategory> newsCategories = new ArrayList<>();
                    for (NewsCategory newsCategory : result) {
                        NewsCategory category = new NewsCategory(newsCategory.id, newsCategory.channel);
                        newsCategories.add(category);
                    }
                    subscriber.onNext(newsCategories);
                }

            }
        });
    }
}
