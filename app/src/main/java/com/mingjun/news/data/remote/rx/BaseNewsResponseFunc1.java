package com.mingjun.news.data.remote.rx;

import com.mingjun.news.data.model.News;
import com.mingjun.news.data.model.NewsList;
import com.mingjun.news.data.remote.response.BaseResponse;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by mingjun on 16/6/28.
 */
public class BaseNewsResponseFunc1 implements Func1<BaseResponse<NewsList>, Observable<ArrayList<News>>> {
    @Override
    public Observable<ArrayList<News>> call(final BaseResponse<NewsList> response) {
        return Observable.create(new Observable.OnSubscribe<ArrayList<News>>() {
            @Override
            public void call(Subscriber<? super ArrayList<News>> subscriber) {
                if (response == null) {
                    subscriber.onError(new NullPointerException());
                }
                // TODO: To be fix
                else if (0  == response.code) {
                    subscriber.onNext(response.data.article);
                }
            }
        });
    }
}
