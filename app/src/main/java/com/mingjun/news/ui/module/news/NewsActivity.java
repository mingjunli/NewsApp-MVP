package com.mingjun.news.ui.module.news;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.mingjun.news.R;
import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.data.remote.NewsRemoteDataSource;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@EActivity(R.layout.activity_news)
public class NewsActivity extends AppCompatActivity {

    @ViewById(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    @ViewById(R.id.content)
    ViewPager mContentViewPager;

    @Bean
    NewsRemoteDataSource mDataSource;

    private NewsFragmentPageAdapter mPageAdapter;

    @AfterViews
    void init() {
        mPageAdapter = new NewsFragmentPageAdapter(getSupportFragmentManager());
        mContentViewPager.setAdapter(mPageAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    void loadData() {
        mDataSource.getNewsCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<NewsCategory>>() {
                    @Override
                    public void call(ArrayList<NewsCategory> newsCategories) {
                        mPageAdapter.setList(newsCategories);
                        mTabs.setViewPager(mContentViewPager);
                    }
                });
    }
}
