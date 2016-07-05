package com.mingjun.mvp;

import android.support.annotation.NonNull;

/**
 * Created by mingjun on 16/7/5.
 */
public interface MvpView {

    void setPresenter(MvpPresenter presenter);

    @NonNull
    public MvpPresenter createPresenter();
}
