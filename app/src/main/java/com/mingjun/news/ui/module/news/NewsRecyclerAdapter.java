package com.mingjun.news.ui.module.news;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingjun.news.R;
import com.mingjun.news.common.util.ImageLoader;
import com.mingjun.news.data.model.News;

import java.util.Date;
import java.util.List;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsRecyclerAdapter extends BaseQuickAdapter<News> {

    public NewsRecyclerAdapter(List<News> data) {
        super(R.layout.item_news_with_pic, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, News news) {
        holder.setText(R.id.title, news.title);
        holder.setText(R.id.time, new Date(news.time).toString());
        holder.setText(R.id.author, news.author);

        ImageView imageView = holder.getView(R.id.image);

        if (TextUtils.isEmpty(news.imgUrl)) {
            imageView.setVisibility(View.GONE);
        }
        else {
            imageView.setVisibility(View.VISIBLE);
            ImageLoader.load(imageView.getContext(), news.imgUrl, imageView);
        }
    }


}
