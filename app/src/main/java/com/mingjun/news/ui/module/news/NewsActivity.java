package com.mingjun.news.ui.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.mingjun.news.R;
import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.RepositoryFactory;
import com.mingjun.news.data.model.NewsCategory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class NewsActivity extends AppCompatActivity {

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;
    @BindView(R.id.content)
    ViewPager mContentViewPager;

    private NewsRepository mDataSource;
    private NewsFragmentPageAdapter mPageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        mDataSource = RepositoryFactory.getNewsRepo();

        initViews();
    }

    void initViews() {
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
