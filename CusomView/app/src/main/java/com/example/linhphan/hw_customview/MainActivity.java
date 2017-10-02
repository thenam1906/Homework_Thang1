package com.example.linhphan.hw_customview;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();
    public FloatingActionButton fb_add;
    public  LinearLayout linearLayout;
    private CustomImage customImage;
    private ScrollView scrollView;
    CustomSpace customSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        addListener();
    }
    private void setupUI() {
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        scrollView = (ScrollView) findViewById(R.id.scroll);
        scrollView.fullScroll(View.FOCUS_DOWN);
        fb_add =(FloatingActionButton) findViewById(R.id.fb_add);
    }
    private void addListener() {
        fb_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomImage();
                selectImage();
            }
        });
    }
    public int randomNumber()
    {
        Random random = new Random();
        int number= random.nextInt(5);
        return number;
    }
    public void selectImage()
    {
        int number= randomNumber();
        switch (number)
        {
            case 1:
            {
                customImage.setImageResource(R.drawable.food_1);
             //   linearLayout.addView(image);
                break;
            }
            case 2:
            {
                customImage.setImageResource(R.drawable.food_2);
                break;
            }
            case 3:
            {
                customImage.setImageResource(R.drawable.food_3);
                break;
            }
            case 4:
            {
                customImage.setImageResource(R.drawable.food_4);
                break;
            }
            case 5:
            {
                customImage.setImageResource(R.drawable.food_5);
                break;
            }
        }
    }
    public void addCustomImage()
    {
        customSpace = new CustomSpace(this);
        customImage = new CustomImage(this);
        linearLayout.addView(customImage);
        linearLayout.addView(customSpace);
    }


}
