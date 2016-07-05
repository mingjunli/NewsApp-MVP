package com.mingjun.news.presenter.news;

import com.mingjun.mvp.MvpPresenter;
import com.mingjun.mvp.lce.LceView;
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
public class NewsListPresenter<V extends LceView> implements MvpPresenter<V> {

    private final NewsRepository mDataSource;
    private final NewsCategory mNewsCategory;

    private LceView mNewsView;

    private CompositeSubscription mSubscriptions;

    public NewsListPresenter(NewsRepository dataSource, NewsCategory category) {
        this.mDataSource = dataSource;
        this.mNewsCategory = category;
    }

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
                        mNewsView.showContent(newsList);
                    }
                }));
    }

    @Override
    public void attachView(V view) {
        mNewsView = view;
        mSubscriptions = new CompositeSubscription();
        loadNews();
    }

    @Override
    public void detachView() {
        mSubscriptions.clear();
    }
}
