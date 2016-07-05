package com.mingjun.news.ui.module.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mingjun.news.data.model.NewsCategory;
import com.mingjun.news.ui.base.adapter.ArrayFragmentPagerAdapter;
import com.mingjun.news.ui.module.news.NewsFragment;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsFragmentPageAdapter extends ArrayFragmentPagerAdapter<NewsCategory> {

    public NewsFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        NewsCategory category = mList.get(position);
        return NewsFragment.newInstance(category);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).channel;
    }
}
