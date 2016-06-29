package com.mingjun.news.data;

import com.mingjun.news.data.remote.NewsRemoteDataSource;

/**
 * Created by mingjun on 16/6/29.
 */
public class RepositoryFactory {

    public static NewsRepository getNewsRepo() {
        return NewsRemoteDataSource.getInstance();
    }
}
