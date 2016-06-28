package com.mingjun.news.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mingjun on 16/6/28.
 */
public class News implements Parcelable {

    /**
     * "title": "这才是中国说话礼仪, 一开口就看出教养!",
     * "url": "http://www.3023.com/17/666941527115700976.html",
     * "img": "http://image.zzd.sm.cn/3253914432259577073.jpg",
     * "author": "社交口才技巧",
     * "time": 1463227267
     */

    public String title;
    public String url;
    public String imgUrl;
    public String author;
    public long time;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.imgUrl);
        dest.writeString(this.author);
        dest.writeLong(this.time);
    }

    public News() {
    }

    protected News(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.imgUrl = in.readString();
        this.author = in.readString();
        this.time = in.readLong();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", author='" + author + '\'' +
                ", time=" + time +
                '}';
    }
}
