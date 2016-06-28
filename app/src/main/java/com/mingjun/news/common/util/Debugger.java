package com.mingjun.news.common.util;

import com.mingjun.news.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by mingjun on 16/6/28.
 */
public class Debugger {

    private static final String TAG = "NewsApp";

    public static void init() {
        Logger.init(TAG);
    }

    public static void d(String message) {
        if (BuildConfig.DEBUG) {
            Logger.d(message);
        }
    }

    public static void w(String message) {
        if (BuildConfig.DEBUG) {
            Logger.w(message);
        }
    }

    public static void e(String message) {
        Logger.e(message);
    }
}
