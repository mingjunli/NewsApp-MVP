package com.mingjun.news.ui.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.NewsApplication;
import com.mingjun.news.R;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.di.HasComponent;
import com.mingjun.news.di.component.DaggerNewsComponent;
import com.mingjun.news.di.component.NewsComponent;
import com.mingjun.news.di.module.ActivityModule;
import com.mingjun.news.presenter.news.NewsPresenter;
import com.mingjun.news.ui.base.BaseActivity;
import com.mingjun.news.ui.module.news.adapter.NewsFragmentPageAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity implements LceView<ArrayList<NewsCategory>>, HasComponent<NewsComponent> {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;
    @BindView(R.id.content)
    ViewPager mContentViewPager;

    @Inject
    NewsPresenter mNewsPresenter;

    private NewsFragmentPageAdapter mPageAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        initViews();

        mNewsPresenter.attachView(this);
        mNewsPresenter.loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNewsPresenter.detachView();
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

    @Override
    public NewsComponent getComponent() {
        return DaggerNewsComponent.builder()
                    .applicationComponent(NewsApplication.get(this).getComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
    }
}
