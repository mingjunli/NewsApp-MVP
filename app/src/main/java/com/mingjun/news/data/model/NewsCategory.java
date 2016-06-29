package com.mingjun.news.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsCategory extends RealmObject implements Parcelable {

    public String id;
    public String channel;
    public int deleted;

    public NewsCategory(String id, String channel) {
        this.id = id;
        this.channel = channel;
    }

    public NewsCategory() {
    }

    @Override
    public String toString() {
        return "NewsCategory{" +
                "id='" + id + '\'' +
                ", channel='" + channel + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.channel);
        dest.writeInt(this.deleted);
    }

    protected NewsCategory(Parcel in) {
        this.id = in.readString();
        this.channel = in.readString();
        this.deleted = in.readInt();
    }

    public static final Creator<NewsCategory> CREATOR = new Creator<NewsCategory>() {
        @Override
        public NewsCategory createFromParcel(Parcel source) {
            return new NewsCategory(source);
        }

        @Override
        public NewsCategory[] newArray(int size) {
            return new NewsCategory[size];
        }
    };
}
