package com.mingjun.news.ui.module.news;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mingjun.news.R;
import com.mingjun.news.common.util.Debugger;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.data.remote.NewsRemoteDataSource;
import com.mingjun.news.data.remote.NewsRemoteDataSource_;

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

        mNewsListView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mNewsListView.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
        Debugger.d("showLoading");
    }

    @Override
    public void dismissLoading() {
        Debugger.d("dismissLoading");
    }

    @Override
    public void showNews(ArrayList<News> newsList) {
        Debugger.d("newsList = " + newsList.size());
        mAdapter.setNewData(newsList);
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter = new NewsListPresenter(
                NewsRemoteDataSource_.getInstance_(this.getContext()), this);
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

}
