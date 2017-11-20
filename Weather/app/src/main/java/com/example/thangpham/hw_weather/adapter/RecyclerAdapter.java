package com.example.thangpham.hw_weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thangpham.hw_weather.R;
import com.example.thangpham.hw_weather.model.WeatherModel;
import com.example.thangpham.hw_weather.model.WeatherModelJSON;

import java.util.ArrayList;

/**
 * Created by LINHPHAN on 11/19/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.WeatherViewHolder> {
    ArrayList<WeatherModel> listResponseWeather;
    Context context;
    int resource;
    public RecyclerAdapter(ArrayList<WeatherModel> listResponseWeather,Context context, int resource) {
        this.listResponseWeather = listResponseWeather;
        this.context=context;
        this.resource=resource;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.tv_day.setText("Ngày: "+(position+1));
        holder.tv_main.setText("Main: "+listResponseWeather.get(position).getMain());
        holder.tv_des.setText("Description: "+listResponseWeather.get(position).getDescription());
        if(listResponseWeather.get(position).getMain().equals("Snow"))
        {
            holder.iv_image.setImageResource(R.drawable.snow);
        }else if(listResponseWeather.get(position).getMain().equals("Clear"))
        {
            holder.iv_image.setImageResource(R.drawable.clear);
        }else if(listResponseWeather.get(position).getMain().equals("Rain"))
        {
            holder.iv_image.setImageResource(R.drawable.rain);
        }
    }

    @Override
    public int getItemCount() {
        return listResponseWeather.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image;
        TextView tv_day;
        TextView tv_main;
        TextView tv_des;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            tv_day = itemView.findViewById(R.id.tv_day);
            tv_main = itemView.findViewById(R.id.tv_main);
            tv_des = itemView.findViewById(R.id.tv_des);
            iv_image = itemView.findViewById(R.id.iv_image);
        }

    }
}
