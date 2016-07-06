package com.mingjun.news.ui.module.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.webkit.WebView;

import com.mingjun.mvp.MvpActivity;
import com.mingjun.mvp.MvpPresenter;
import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.R;
import com.mingjun.news.data.model.News;
import com.mingjun.news.presenter.news.NewsDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends MvpActivity implements LceView<News> {

    @BindView(R.id.web_view)
    WebView mWebView;

    private News mNews;

    private static final String EXTRA_KEY_NEWS = "extra_news";

    public static void launch(Context context, News news) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(EXTRA_KEY_NEWS, news);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        mNews = getIntent().getParcelableExtra(EXTRA_KEY_NEWS);
    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return new NewsDetailPresenter(mNews);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showContent(News data) {
        mWebView.loadUrl(data.url);
    }

    @Override
    public void showError(Throwable e) {

    }
}
