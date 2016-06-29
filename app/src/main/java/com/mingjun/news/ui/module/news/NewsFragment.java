package com.mingjun.news.ui.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingjun.news.R;
import com.mingjun.news.common.util.Debugger;
import com.mingjun.news.data.RepositoryFactory;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsCategory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsFragment extends Fragment implements NewsContract.View {

    @BindView(R.id.news_list)
    RecyclerView mNewsListView;

    private NewsRecyclerAdapter mAdapter;
    private NewsContract.Presenter mPresenter;

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
        mPresenter = new NewsListPresenter(RepositoryFactory.getNewsRepo(), this, mCategory);
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
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

}
