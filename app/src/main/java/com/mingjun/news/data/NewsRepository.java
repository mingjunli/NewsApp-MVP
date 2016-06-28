package com.mingjun.news.data;

import com.mingjun.news.data.model.News;

import java.util.List;

import rx.Observable;

/**
 * Created by mingjun on 16/6/28.
 */
public interface NewsRepository {

    Observable<List<News>> getNewsByCategory(String category, int page);
}
