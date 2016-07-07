package com.mingjun.news.data.remote;

import com.mingjun.news.NewsApplication;
import com.mingjun.news.common.util.Debugger;
import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.db.DbFactory;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.data.model.NewsDetail;
import com.mingjun.news.data.remote.rx.BaseNewsResponseFunc1;
import com.mingjun.news.data.remote.service.NewsService;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsDataSource implements NewsRepository {

    NewsService mNewsService;

    @Inject
    public NewsDataSource(NewsService service) {
        this.mNewsService = service;
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

    @Override
    public Observable<NewsDetail> getNewsDetail(final News news) {

        return Observable.create(new Observable.OnSubscribe<NewsDetail>() {
            @Override
            public void call(Subscriber<? super NewsDetail> subscriber) {

                try {
                    Request request = new Request.Builder().url(news.url).build();
                    Response response = HttpClientBuilder.build().newCall(request).execute();

                    NewsDetail newsDetail = new NewsDetail();
                    newsDetail.author = news.author;
                    newsDetail.time = news.time;
                    newsDetail.title = news.title;
                    newsDetail.imgUrl = news.imgUrl;
                    newsDetail.url = news.url;

                    String source = response.body().string();

                    int start = source.indexOf("<div id=\"main\" class=\"article\">");
                    int end = source.indexOf("<div class=\"share\">");
                    if (end < 0) {
                        end = source.indexOf("<div class=\"box\"><h2>相关</h2></div>");
                    }

                    int length = source.length();

                    if (start > 0 && start < length
                            && end > start && end < length) {
                        source = source.substring(start, end);
                    }

                    newsDetail.content = source;

                    subscriber.onNext(newsDetail);

                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
}
