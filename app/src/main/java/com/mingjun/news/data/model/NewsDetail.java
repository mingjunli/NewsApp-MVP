package com.mingjun.news.data.model;

import android.os.Parcel;

/**
 * Created by mingjun on 16/7/6.
 */
public class NewsDetail extends News {

    public String content;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.content);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.imgUrl);
        dest.writeString(this.author);
        dest.writeLong(this.time);
    }

    public NewsDetail() {
    }

    protected NewsDetail(Parcel in) {
        super(in);
        this.content = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.imgUrl = in.readString();
        this.author = in.readString();
        this.time = in.readLong();
    }

    public static final Creator<NewsDetail> CREATOR = new Creator<NewsDetail>() {
        @Override
        public NewsDetail createFromParcel(Parcel source) {
            return new NewsDetail(source);
        }

        @Override
        public NewsDetail[] newArray(int size) {
            return new NewsDetail[size];
        }
    };
}
