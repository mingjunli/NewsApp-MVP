package com.mingjun.news.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by mingjun on 16/6/28.
 */
public class NewsList implements Parcelable {

    public String channel;
    public ArrayList<News> article;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.channel);
        dest.writeTypedList(this.article);
    }

    public NewsList() {
    }

    protected NewsList(Parcel in) {
        this.channel = in.readString();
        this.article = in.createTypedArrayList(News.CREATOR);
    }

    public static final Parcelable.Creator<NewsList> CREATOR = new Parcelable.Creator<NewsList>() {
        @Override
        public NewsList createFromParcel(Parcel source) {
            return new NewsList(source);
        }

        @Override
        public NewsList[] newArray(int size) {
            return new NewsList[size];
        }
    };
}
