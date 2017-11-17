package com.example.thangpham.hw_weather;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ThangPham on 11/16/2017.
 */

public interface WeatherInterface {

    @GET("weather")
    Call<WeatherModelJSON.ResponseWeather> getInfoWeather(
            @Query("q") String name,
            @Query("appid") String appid
    );

}
