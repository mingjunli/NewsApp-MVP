package com.mingjun.news.data;

import com.mingjun.news.data.model.News;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by mingjun on 16/6/28.
 */
public interface NewsRepository {

    Observable<ArrayList<News>> getNewsByCategory(String category, int page);
}
