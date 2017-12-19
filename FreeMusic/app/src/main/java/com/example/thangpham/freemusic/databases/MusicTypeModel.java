package com.example.thangpham.freemusic.databases;

import io.realm.RealmObject;

/**
 * Created by LINHPHAN on 11/18/2017.
 */

public class MusicTypeModel extends RealmObject{
    public String key;
    public String id;
    public int imageID;
    public boolean isFavorite;

}
