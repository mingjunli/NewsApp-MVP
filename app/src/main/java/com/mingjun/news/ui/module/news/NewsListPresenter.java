package com.mingjun.news.ui.module.news;

import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.data.remote.rx.ResponseObserver;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsListPresenter implements NewsContract.Presenter{

    private final NewsRepository mDataSource;
    private final NewsCategory mNewsCategory;

    private final NewsContract.View mNewsView;

    private CompositeSubscription mSubscriptions;

    public NewsListPresenter(NewsRepository dataSource, NewsContract.View view, NewsCategory category) {
        this.mDataSource = dataSource;
        this.mNewsView = view;
        this.mNewsCategory = category;

        mSubscriptions = new CompositeSubscription();
        mNewsView.setPresenter(this);
    }

    @Override
    public void loadNews() {
        mNewsView.showLoading();
        mSubscriptions.add(mDataSource.getNewsByCategory(mNewsCategory.id, 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        mNewsView.dismissLoading();
                    }
                })
                .subscribe(new ResponseObserver<ArrayList<News>>() {
                    @Override
                    public void onError(int errorCode) {

                    }

                    @Override
                    public void onSuccess(ArrayList<News> newsList) {
                        mNewsView.showNews(newsList);
                    }
                }));
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
