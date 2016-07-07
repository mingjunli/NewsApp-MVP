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
        Debugger.d("onCreate:" + this.getClass().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Debugger.d("onDestroy:" + this.getClass().getName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Debugger.d("onActivityCreated:" + this.getClass().getName());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Debugger.d("onViewCreated:" + this.getClass().getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Debugger.d("onCreateView:" + this.getClass().getName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Debugger.d("onDestroyView:" + this.getClass().getName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Debugger.d("onAttach:" + this.getClass().getName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Debugger.d("onDetach:" + this.getClass().getName());
    }


    @Override
    public void onStart() {
        super.onStart();
        Debugger.d("onStart:" + this.getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Debugger.d("onResume:" + this.getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Debugger.d("onPause:" + this.getClass().getName());
    }

    @Override
    public void onStop() {
        super.onStop();
        Debugger.d("onStop:" + this.getClass().getName());
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
