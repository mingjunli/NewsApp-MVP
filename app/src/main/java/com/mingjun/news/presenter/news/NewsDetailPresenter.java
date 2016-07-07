package com.mingjun.news.presenter.news;

import com.mingjun.mvp.BaseMvpPresenter;
import com.mingjun.mvp.MvpPresenter;
import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsDetail;
import com.mingjun.news.data.remote.rx.ResponseObserver;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by mingjun on 16/7/6.
 */
public class NewsDetailPresenter extends BaseMvpPresenter<LceView<NewsDetail>> {

    private final NewsRepository mDataSource;

    @Inject
    public NewsDetailPresenter(NewsRepository dataSource) {
        mDataSource = dataSource;
    }

    public void loadNewsDetail(News news) {
        getMvpView().showLoading();
        mDataSource.getNewsDetail(news)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getMvpView().dismissLoading();
                    }
                })
                .subscribe(new ResponseObserver<NewsDetail>() {
                    @Override
                    public void onError(int errorCode) {
                        getMvpView().showError(null);
                    }

                    @Override
                    public void onSuccess(NewsDetail newsDetail) {
                        getMvpView().showContent(newsDetail);
                    }
                });
    }
}
