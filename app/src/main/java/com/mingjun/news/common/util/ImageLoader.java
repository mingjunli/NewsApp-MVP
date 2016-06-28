package com.mingjun.news.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by mingjun on 16/6/28.
 */
public class ImageLoader {

    /**
     * Load Image
     *
     * @param context
     * @param url
     * @param view
     */
    public static void load(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(view);

    }

}
