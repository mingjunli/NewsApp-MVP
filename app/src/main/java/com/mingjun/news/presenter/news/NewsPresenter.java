package com.mingjun.news.presenter.news;

import com.mingjun.mvp.MvpPresenter;
import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.model.NewsCategory;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mingjun on 16/7/5.
 */
public class NewsPresenter<V extends LceView> implements MvpPresenter<V> {

    private final NewsRepository mDataSource;

    private LceView mNewsView;

    private CompositeSubscription mSubscriptions;

    public NewsPresenter(NewsRepository dataSource) {
        this.mDataSource = dataSource;
    }

    void loadData() {
        mDataSource.getNewsCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<NewsCategory>>() {
                    @Override
                    public void call(ArrayList<NewsCategory> newsCategories) {
                        mNewsView.showContent(newsCategories);
                    }
                });
    }

    @Override
    public void attachView(V view) {
        mNewsView = view;
        mSubscriptions = new CompositeSubscription();
        loadData();
    }

    @Override
    public void detachView() {
        mSubscriptions.clear();
    }
}
