package com.example.linhphan.nhap_hw2;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
        ScrollView scrollView;
        LinearLayout linearLayout;
        FloatingActionButton fb_add;

        CustomImageView customImageView; // khi mình muốn CustomImageView thành ImageView thì mình coi CustomImageView chính là 1 ImageView
        int image[] ={R.drawable.food_1,R.drawable.food_2,R.drawable.food_3,R.drawable.food_4,R.drawable.food_5};
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setupUI();
            addListener();
    }
    public void addListener()
    {
        fb_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                addImageView();
                customImageView.setImageResource(image[randomNumber()]);

            }
        });
    }
    private void setupUI() {


        fb_add = (FloatingActionButton) findViewById(R.id.fb_add);
        linearLayout = (LinearLayout) findViewById(R.id.linear_image);
        scrollView= (ScrollView) findViewById(R.id.srcoll);

    }
    public int randomNumber()
    {
        return new Random().nextInt(5);
    }
    public void addImageView()
    {

        customImageView = new CustomImageView(this);
        customImageView.setPadding(5,5,5,5);
        customImageView.setScaleType(ImageView.ScaleType.CENTER);
        customImageView.setCropToPadding(true);
        scrollView.fullScroll(View.FOCUS_DOWN);
        linearLayout.addView(customImageView);

    }
}
