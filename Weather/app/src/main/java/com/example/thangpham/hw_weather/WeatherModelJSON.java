package com.example.thangpham.hw_weather;

import java.util.List;

/**
 * Created by ThangPham on 11/16/2017.
 */

public class WeatherModelJSON {

    public class ResponseWeather
    {
        List<InfoWeather> weather;

        public class InfoWeather {
            public int id;
            public String main;
            public String description;

        }

    }


}
