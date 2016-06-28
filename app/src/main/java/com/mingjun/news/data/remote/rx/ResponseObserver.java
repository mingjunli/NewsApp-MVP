package com.mingjun.news.data.remote.rx;

import com.mingjun.news.common.util.Debugger;

import rx.Subscriber;

/**
 * Created by mingjun on 16/6/28.
 */
public abstract class ResponseObserver<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        Debugger.d("onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Debugger.d("onError:" + e);
        onError(0);
    }

    @Override
    public void onNext(T t) {
        Debugger.d("onNext:" + t);
        onSuccess(t);
    }

    public abstract void onError(int errorCode);

    public abstract void onSuccess(T t);
}
