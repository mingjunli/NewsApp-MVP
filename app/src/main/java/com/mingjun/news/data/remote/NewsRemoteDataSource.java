package com.mingjun.news.data.remote;

import com.mingjun.news.data.NewsRepository;
import com.mingjun.news.data.model.News;

import java.util.List;

import rx.Observable;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsRemoteDataSource implements NewsRepository {

    @Override
    public Observable<List<News>> getNewsByCategory(String category, int page) {
        return null;
    }
}
