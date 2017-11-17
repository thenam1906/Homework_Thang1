package com.example.thangpham.hw_weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText etCity;
    Button btSearch;
    TextView tv_des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = findViewById(R.id.et_city);
        btSearch= findViewById(R.id.btSearch);
        tv_des= findViewById(R.id.tv_des);

         final WeatherInterface weatherInterface = RetrofitInstance.getInstance().create(WeatherInterface.class);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = etCity.getText().toString();
                weatherInterface.getInfoWeather(cityName,"36659f145f4bb9b56ca96bc519225d0e").enqueue(new Callback<WeatherModelJSON.ResponseWeather>() {

                    @Override
                    public void onResponse(Call<WeatherModelJSON.ResponseWeather> call, Response<WeatherModelJSON.ResponseWeather> response) {
                        if(response.isSuccessful())
                        {

                            WeatherModelJSON.ResponseWeather responseWeather = response.body();
                            String info="";
                            for(int i=0;i<responseWeather.weather.size();i++ )
                            {
                               info += "id: "+responseWeather.weather.get(i).id+"\r\n"+
                                       "main: "+responseWeather.weather.get(i).main+"\r\n"+
                                       "description: "+responseWeather.weather.get(i).description+"\r\n";
                            }

                            tv_des.setText(info);
                        }
                        else
                        {
                            tv_des.setText("Not found city");
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherModelJSON.ResponseWeather> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
