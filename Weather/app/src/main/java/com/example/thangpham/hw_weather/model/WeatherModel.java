package com.example.thangpham.hw_weather.model;

/**
 * Created by ThangPham on 11/19/2017.
 */

public class WeatherModel {
    String main;
    String description;

    public WeatherModel(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
