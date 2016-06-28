package com.mingjun.news.ui.module.news;

import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.model.News;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsListPresenter implements NewsContract.Presenter{

    private final NewsRepository mDataSource;
    private final NewsContract.View mNewsView;

    private CompositeSubscription mSubscriptions;

    public NewsListPresenter(NewsRepository dataSource, NewsContract.View view) {
        this.mDataSource = dataSource;
        this.mNewsView = view;

        mSubscriptions = new CompositeSubscription();
        mNewsView.setPresenter(this);
    }

    @Override
    public void loadNews() {
        mNewsView.showLoading();
        mDataSource.getNewsByCategory("popular", 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<News>>() {
                    @Override
                    public void call(ArrayList<News> newsList) {
                        mNewsView.showNews(newsList);
                    }
                });
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void subscribe() {
        loadNews();
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
