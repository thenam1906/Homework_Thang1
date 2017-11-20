package com.example.thangpham.hw_weather.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thangpham.hw_weather.R;
import com.example.thangpham.hw_weather.adapter.RecyclerAdapter;
import com.example.thangpham.hw_weather.model.RetrofitInstance;
import com.example.thangpham.hw_weather.model.WeatherInterface;
import com.example.thangpham.hw_weather.model.WeatherModel;
import com.example.thangpham.hw_weather.model.WeatherModelJSON;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    List<WeatherModelJSON.ResponseList.ResponseWeather> listResponseWeather;

    EditText etCity;
    Button btSearch;
    RecyclerView rv_daily;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = findViewById(R.id.et_city);
        rv_daily= findViewById(R.id.rv_weather);
        btSearch= findViewById(R.id.btSearch);

         final WeatherInterface weatherInterface = RetrofitInstance.getInstance().create(WeatherInterface.class);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = etCity.getText().toString();
                weatherInterface.getResponseList(cityName,WeatherInterface.appid).
                        enqueue(new Callback<WeatherModelJSON.ResponseList>() {
                    @Override
                    public void onResponse(Call<WeatherModelJSON.ResponseList> call, Response<WeatherModelJSON.ResponseList> response) {
                        if(response.isSuccessful())
                        {
                            ArrayList<WeatherModel> listModel =  new ArrayList<>();
                            WeatherModelJSON.ResponseList list = response.body();

                            for(int i=0;i<list.list.size();i++)
                            {
                                String main = list.list.get(i).weather.get(0).main;
                                String description = list.list.get(i).weather.get(0).description;
                                listModel.add(new WeatherModel(main,description));
                                Log.d(TAG, "onResponse: "+main+" "+description);
                            }
                            RecyclerAdapter  adapter = new RecyclerAdapter( listModel,MainActivity.this,R.layout.item_list_daily);
                            rv_daily.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            rv_daily.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Not found city", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherModelJSON.ResponseList> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
