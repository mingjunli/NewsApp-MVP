package com.mingjun.news.presenter.news;

import com.mingjun.mvp.BaseMvpPresenter;
import com.mingjun.mvp.MvpPresenter;
import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.common.util.Debugger;
import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.data.remote.NewsDataSource;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mingjun on 16/7/5.
 */
public class NewsPresenter extends BaseMvpPresenter<LceView> {

    private final NewsRepository mDataSource;

    @Inject
    public NewsPresenter(NewsRepository dataSource) {
        mDataSource = dataSource;
    }

    public void loadData() {
        Debugger.d("loadData begin======");
        mDataSource.getNewsCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<NewsCategory>>() {
                    @Override
                    public void call(ArrayList<NewsCategory> newsCategories) {
                        getMvpView().showContent(newsCategories);
                    }
                });
    }

}
