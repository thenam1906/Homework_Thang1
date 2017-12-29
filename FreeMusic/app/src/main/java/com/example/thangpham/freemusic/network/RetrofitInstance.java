package com.example.thangpham.freemusic.network;

import android.support.annotation.RestrictTo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by ThangPham on 11/15/2017.
 */

public class  RetrofitInstance {
    private static Retrofit retrofit;
    private static Retrofit retrofitXML;
    public static Retrofit getInstance()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://music-api-for-tk.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getInstanceXML()
    {
        if(retrofitXML==null)
        {
            retrofitXML = new Retrofit.Builder()
                    .baseUrl("https://music-api-for-tk.herokuapp.com/")
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return retrofitXML;
    }
}
