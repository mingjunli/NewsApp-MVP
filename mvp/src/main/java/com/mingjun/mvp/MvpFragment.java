package com.mingjun.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by mingjun on 16/7/5.
 */
public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>>
        extends Fragment implements MvpView {

    public MvpPresenter mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }

        setPresenter(mPresenter);
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
