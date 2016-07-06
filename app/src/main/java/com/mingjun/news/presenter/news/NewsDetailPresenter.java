package com.mingjun.news.presenter.news;

import com.mingjun.mvp.MvpPresenter;
import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.data.model.News;

/**
 * Created by mingjun on 16/7/6.
 */
public class NewsDetailPresenter<V extends LceView> implements MvpPresenter<V> {

    private News mNews;

    public NewsDetailPresenter(News news) {
        mNews = news;
    }

    @Override
    public void attachView(V view) {
        view.showContent(mNews);
    }

    @Override
    public void detachView() {

    }
}
