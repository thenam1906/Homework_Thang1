package com.example.thangpham.freemusic.events;

import com.example.thangpham.freemusic.databases.TopSongModel;

/**
 * Created by ThangPham on 11/29/2017.
 */

public class OnClickTopSongEvent {
    public TopSongModel topSongModel;

    public OnClickTopSongEvent(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }
}
