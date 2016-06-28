package com.mingjun.news.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsCategory implements Parcelable {

    public String id;
    public String channel;

    public NewsCategory(String id, String channel) {
        this.id = id;
        this.channel = channel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.channel);
    }

    public NewsCategory() {
    }

    protected NewsCategory(Parcel in) {
        this.id = in.readString();
        this.channel = in.readString();
    }

    public static final Parcelable.Creator<NewsCategory> CREATOR = new Parcelable.Creator<NewsCategory>() {
        @Override
        public NewsCategory createFromParcel(Parcel source) {
            return new NewsCategory(source);
        }

        @Override
        public NewsCategory[] newArray(int size) {
            return new NewsCategory[size];
        }
    };

    @Override
    public String toString() {
        return "NewCategory{" +
                "id='" + id + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}
