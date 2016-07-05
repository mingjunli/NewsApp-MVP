package com.mingjun.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mingjun on 16/7/5.
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends AppCompatActivity implements MvpView {

    public MvpPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mPresenter == null) {
            mPresenter = createPresenter();
        }

        mPresenter.attachView(this);
        setPresenter(mPresenter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void setPresenter(MvpPresenter presenter) {
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public abstract P createPresenter();
}
