package com.mingjun.news.data.remote.service;

import com.mingjun.news.data.model.NewsList;
import com.mingjun.news.data.remote.response.BaseResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mingjun on 16/6/28.
 */
public interface NewsService {

    @Headers("apikey: f00c18d7afd240f8fac9f138219df794")
    @GET("3023/news/channel")
    Observable<BaseResponse<NewsList>> getNewsByCategory(
            @Query("id") String category, @Query("page") int page);
}
