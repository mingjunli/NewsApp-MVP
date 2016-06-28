package com.mingjun.news.data.remote.service;

import com.mingjun.news.data.model.NewsList;
import com.mingjun.news.data.remote.response.BaseResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by mingjun on 16/6/28.
 */
public interface NewsService {

    @GET("3023/news/channel")
    Observable<BaseResponse<NewsList>> getNewsByCategory(String category, int page);
}
