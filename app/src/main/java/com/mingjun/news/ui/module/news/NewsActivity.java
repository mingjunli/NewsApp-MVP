package com.mingjun.news.ui.module.news;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.mingjun.news.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_news)
public class NewsActivity extends AppCompatActivity {

    @ViewById(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    @ViewById(R.id.content)
    ViewPager mContentViewPager;
}
