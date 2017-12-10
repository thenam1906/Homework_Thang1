package com.example.thangpham.freemusic.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ThangPham on 11/15/2017.
 */

public interface MusicInterface {
    @GET("api")
    Call<MusicTypeResponseJSON> getMusicTypes();

    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={id}/explicit=true/json")
    Call<TopSongResponseJSON> getTopSong(@Path("id") String id);

    @GET("https://tk-gx.herokuapp.com/api/audio")
    Call<SearchResponseJSON> getSearchSong(@Query("search_terms") String search);


}
