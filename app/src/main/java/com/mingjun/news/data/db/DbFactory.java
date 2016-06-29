package com.mingjun.news.data.db;

import android.content.Context;

import com.mingjun.news.data.model.NewsCategory;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by mingjun on 16/6/29.
 */
public class DbFactory {

    public static Realm getRealm(Context context) {
        return Realm.getInstance(
                new RealmConfiguration.Builder(context).name("news_data.realm").build());
    }

    public static void initNewsCategories(Context context) {
        Realm realm = getRealm(context);

        realm.beginTransaction();

        NewsCategory popular = realm.createObject(NewsCategory.class);
        popular.id = "popular";
        popular.channel = "热点";

        NewsCategory recomm = realm.createObject(NewsCategory.class);
        recomm.id = "recomm";
        recomm.channel = "推荐";

        NewsCategory sports = realm.createObject(NewsCategory.class);
        sports.id = "sports";
        sports.channel = "体育";

        NewsCategory tech = realm.createObject(NewsCategory.class);
        tech.id = "tech";
        tech.channel = "科技";

        NewsCategory history = realm.createObject(NewsCategory.class);
        history.id = "history";
        history.channel = "历史";

        NewsCategory ent = realm.createObject(NewsCategory.class);
        ent.id = "ent";
        ent.channel = "娱乐";

        NewsCategory finance = realm.createObject(NewsCategory.class);
        finance.id = "finance";
        finance.channel = "财经";

        NewsCategory auto = realm.createObject(NewsCategory.class);
        auto.id = "auto";
        auto.channel = "汽车";

        NewsCategory society = realm.createObject(NewsCategory.class);
        society.id = "society";
        society.channel = "社会";

        realm.commitTransaction();
    }
}
