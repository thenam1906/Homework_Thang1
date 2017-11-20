package com.example.thangpham.hw_weather.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ThangPham on 11/16/2017.
 */

public interface WeatherInterface {
    public static String appid="b1b15e88fa797225412429c1c50c122a1";
    @GET("daily")
    Call<WeatherModelJSON.ResponseList> getResponseList(
            @Query("q") String q,
            @Query("appid") String appid
    );


}
