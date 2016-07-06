package com.mingjun.news.presenter.news;

import com.mingjun.mvp.MvpPresenter;
import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsDetail;
import com.mingjun.news.data.remote.rx.ResponseObserver;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by mingjun on 16/7/6.
 */
public class NewsDetailPresenter<V extends LceView> implements MvpPresenter<V> {

    private final NewsRepository mDataSource;
    private News mNews;

    private LceView mNewsDetailView;

    public NewsDetailPresenter(NewsRepository dataSource, News news) {
        mDataSource = dataSource;
        mNews = news;
    }

    private void loadNewsDetail() {
        mNewsDetailView.showLoading();
        mDataSource.getNewsDetail(mNews)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        mNewsDetailView.dismissLoading();
                    }
                })
                .subscribe(new ResponseObserver<NewsDetail>() {
                    @Override
                    public void onError(int errorCode) {
                        mNewsDetailView.showError(null);
                    }

                    @Override
                    public void onSuccess(NewsDetail newsDetail) {
                        mNewsDetailView.showContent(newsDetail);
                    }
                });
    }

    @Override
    public void attachView(V view) {
        mNewsDetailView = view;
        loadNewsDetail();
    }

    @Override
    public void detachView() {

    }
}
