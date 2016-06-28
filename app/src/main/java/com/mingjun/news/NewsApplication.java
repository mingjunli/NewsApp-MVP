package com.mingjun.news;

import android.app.Application;

import com.mingjun.news.common.util.Debugger;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EApplication;

/**
 * Created by mingjun on 16/6/28.
 */
@EApplication
public class NewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // init debugger
        Debugger.init();
    }
}
