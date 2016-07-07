package com.mingjun.news.presenter.news;

import com.mingjun.mvp.BaseMvpPresenter;
import com.mingjun.mvp.MvpPresenter;
import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.data.remote.rx.ResponseObserver;
import com.mingjun.news.di.PerActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsListPresenter extends BaseMvpPresenter<LceView> {

    private final NewsRepository mDataSource;

    @Inject
    public NewsListPresenter(NewsRepository dataSource) {
        this.mDataSource = dataSource;
    }

    public void loadNewsList(NewsCategory category) {
        getMvpView().showLoading();
        mDataSource.getNewsByCategory(category.id, 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getMvpView().dismissLoading();
                    }
                })
                .subscribe(new ResponseObserver<ArrayList<News>>() {
                    @Override
                    public void onError(int errorCode) {

                    }

                    @Override
                    public void onSuccess(ArrayList<News> newsList) {
                        getMvpView().showContent(newsList);
                    }
                });
    }
}
