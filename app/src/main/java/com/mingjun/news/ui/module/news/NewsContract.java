package com.mingjun.news.ui.module.news;

import com.mingjun.news.data.model.News;
import com.mingjun.news.ui.base.BasePresenter;
import com.mingjun.news.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by mingjun on 16/6/28.
 */
public interface NewsContract {

    interface Presenter extends BasePresenter {

        void loadNews();

        void loadMore();

        void refresh();
    }

    interface View extends BaseView<Presenter> {

        void showNews(ArrayList<News> newsList);
    }
}
