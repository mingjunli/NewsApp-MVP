package com.mingjun.news.ui.module.news;

import android.support.v4.app.Fragment;

import com.mingjun.news.R;
import com.mingjun.news.data.model.NewsCategory;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by mingjun on 16/6/28.
 */
@EFragment(R.layout.fragment_news)
public class NewsFragment extends Fragment {

    @FragmentArg
    NewsCategory mCategory;
}
