package com.mingjun.news.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingjun.news.common.util.Debugger;
import com.mingjun.news.di.HasComponent;

/**
 * Created by mingjun on 16/7/7.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Debugger.d("onCreate:" + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Debugger.d("onDestroy:" + this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Debugger.d("onActivityCreated:" + this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Debugger.d("onViewCreated:" + this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Debugger.d("onCreateView:" + this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Debugger.d("onDestroyView:" + this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Debugger.d("onAttach:" + this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Debugger.d("onDetach:" + this);
    }


    @Override
    public void onStart() {
        super.onStart();
        Debugger.d("onStart:" + this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Debugger.d("onResume:" + this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Debugger.d("onPause:" + this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Debugger.d("onStop:" + this);
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
