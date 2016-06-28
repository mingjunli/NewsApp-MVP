package com.mingjun.news.ui.module.news;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.mingjun.news.R;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsCategory;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by mingjun on 16/6/28.
 */
@EFragment(R.layout.fragment_news)
public class NewsFragment extends Fragment implements NewsContract.View {

    @FragmentArg
    NewsCategory mCategory;

    @ViewById(R.id.news_list)
    RecyclerView mNewsListView;

    NewsRecyclerAdapter mAdapter;

    private NewsContract.Presenter mPresenter;

    @AfterViews
    void init() {
        mAdapter = new NewsRecyclerAdapter(null);
        mNewsListView.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showNews(ArrayList<News> newsList) {
        mAdapter.setNewData(newsList);
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

}
