package com.example.thangpham.freemusic.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ThangPham on 11/15/2017.
 */

public interface MusicInterface {
    @GET("api")
    Call<MusicTypeResponseJSON> getMusicTypes();

    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={id}/explicit=true/json")
    Call<TopSongResponseJSON> getTopSong(@Path("id") String id);

}
