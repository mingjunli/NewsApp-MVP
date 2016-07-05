package com.mingjun.news.ui.module.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.mingjun.mvp.MvpActivity;
import com.mingjun.mvp.MvpPresenter;
import com.mingjun.mvp.MvpView;
import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.R;
import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.RepositoryFactory;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.presenter.news.NewsPresenter;
import com.mingjun.news.ui.module.news.adapter.NewsFragmentPageAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class NewsActivity extends MvpActivity implements LceView<ArrayList<NewsCategory>> {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;
    @BindView(R.id.content)
    ViewPager mContentViewPager;

    private NewsFragmentPageAdapter mPageAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        initViews();
    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new NewsPresenter(RepositoryFactory.getNewsRepo());
    }

    void initViews() {
        mPageAdapter = new NewsFragmentPageAdapter(getSupportFragmentManager());
        mContentViewPager.setAdapter(mPageAdapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showContent(ArrayList<NewsCategory> data) {
        mPageAdapter.setList(data);
        mTabs.setViewPager(mContentViewPager);
    }

    @Override
    public void showError(Throwable e) {

    }
}
