package com.mingjun.news;

import android.app.Application;
import android.content.Context;

import com.mingjun.news.common.util.Debugger;
import com.mingjun.news.data.db.DbFactory;
import com.mingjun.news.di.component.ApplicationComponent;
import com.mingjun.news.di.component.DaggerApplicationComponent;
import com.mingjun.news.di.module.ApplicationModule;

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

    public static NewsApplication get(Context context) {
        return (NewsApplication) context.getApplicationContext();
    }

    ApplicationComponent mApplicationComponent;
    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
