package com.mingjun.news;

import android.app.Application;

import com.mingjun.news.common.util.Debugger;
import com.mingjun.news.data.db.DbFactory;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsApplication extends Application {

    private static NewsApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        // init debugger
        Debugger.init();

        // init news categories
        DbFactory.initNewsCategories(getApplicationContext());
    }

    public static NewsApplication getInstance() {
        return sInstance;
    }
}
