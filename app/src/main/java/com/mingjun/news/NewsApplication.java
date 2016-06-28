package com.mingjun.news;

import android.app.Application;

import com.mingjun.news.common.util.Debugger;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // init debugger
        Debugger.init();
    }
}
