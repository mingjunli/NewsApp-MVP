package com.mingjun.news.ui.module.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingjun.mvp.MvpFragment;
import com.mingjun.mvp.MvpPresenter;
import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.R;
import com.mingjun.news.common.util.Debugger;
import com.mingjun.news.data.RepositoryFactory;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.presenter.news.NewsListPresenter;
import com.mingjun.news.ui.module.news.adapter.NewsRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsFragment extends MvpFragment implements LceView<ArrayList<News>> {

    @BindView(R.id.news_list)
    RecyclerView mNewsListView;

    private NewsRecyclerAdapter mAdapter;

    public static final String ARGUMENT = "category";
    private NewsCategory mCategory;

    public static NewsFragment newInstance(NewsCategory category) {

        NewsFragment fragment = new NewsFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT, category);
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategory = getArguments().getParcelable(ARGUMENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
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
    public void showContent(ArrayList<News> data) {
        Debugger.d("newsList = " + data.size());
        mAdapter.setNewData(data);
    }

    @Override
    public void showError(Throwable e) {
        Debugger.d("error: " + e);
    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new NewsListPresenter(RepositoryFactory.getNewsRepo(), mCategory);
    }
}
