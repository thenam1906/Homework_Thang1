package com.example.thangpham.freemusic.events;

import com.example.thangpham.freemusic.databases.MusicTypeModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ThangPham on 11/22/2017.
 */

public class OnClickMusicTypeEvent {
    public MusicTypeModel musicTypeModel;

    public OnClickMusicTypeEvent(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }


}
