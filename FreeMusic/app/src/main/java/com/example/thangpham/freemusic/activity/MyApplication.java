package com.example.thangpham.freemusic.activity;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by ThangPham on 12/16/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
