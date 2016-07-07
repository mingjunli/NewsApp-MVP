package com.mingjun.news.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mingjun.news.NewsApplication;
import com.mingjun.news.di.component.ActivityComponent;
import com.mingjun.news.di.component.DaggerActivityComponent;
import com.mingjun.news.di.module.ActivityModule;

/**
 * Created by mingjun on 16/7/7.
 */
public class BaseActivity extends AppCompatActivity {


    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(NewsApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
