package com.example.thangpham.hw_weather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ThangPham on 11/15/2017.
 */

public class RetrofitInstance {
    private static Retrofit retrofit;
    public static Retrofit getInstance()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://samples.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
