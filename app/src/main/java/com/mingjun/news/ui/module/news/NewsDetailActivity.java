package com.mingjun.news.ui.module.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.mingjun.mvp.lce.LceView;
import com.mingjun.news.NewsApplication;
import com.mingjun.news.R;
import com.mingjun.news.common.util.Debugger;
import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsDetail;
import com.mingjun.news.di.HasComponent;
import com.mingjun.news.di.component.DaggerNewsComponent;
import com.mingjun.news.di.component.NewsComponent;
import com.mingjun.news.di.module.ActivityModule;
import com.mingjun.news.presenter.news.NewsDetailPresenter;
import com.mingjun.news.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends BaseActivity implements LceView<NewsDetail>, HasComponent<NewsComponent>{

    @BindView(R.id.web_view)
    WebView mWebView;

    @Inject
    NewsDetailPresenter mNewsDetailPresenter;

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
        getComponent().inject(this);

        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        mNews = getIntent().getParcelableExtra(EXTRA_KEY_NEWS);
        mNewsDetailPresenter.attachView(this);
        mNewsDetailPresenter.loadNewsDetail(mNews);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNewsDetailPresenter.detachView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showContent(NewsDetail data) {
        Debugger.d("showContent detail.content = " + data.content);

        mWebView.loadDataWithBaseURL("about:blank", data.content, "text/html", "utf-8", null);
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
