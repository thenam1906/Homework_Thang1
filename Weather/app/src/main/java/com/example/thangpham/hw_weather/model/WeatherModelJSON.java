package com.example.thangpham.hw_weather.model;

import java.util.List;

/**
 * Created by ThangPham on 11/16/2017.
 */

public class WeatherModelJSON {
    // tên biến JSON phải giống 100% với bên postman ko sẽ báo null

    public class ResponseList
    {
        public List<ResponseWeather> list;

        public class ResponseWeather
        {
            public List<InfoWeather> weather;


            public class InfoWeather {

                public String main;
                public String description;

                public InfoWeather( String main, String description) {
                    this.main = main;
                    this.description = description;
                }
                public String getMain() {
                    return main;
                }

                public String getDescription() {
                    return description;
                }

            }

        }
    }


}
