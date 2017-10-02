package com.example.linhphan.hw3_gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gvImage;
    ImageAdapter imageAdapter;
    ArrayList<Integer> listImage;

    ArrayList<String> listText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();

        setAdapter();
    }

    private void setupUI() {
        gvImage = (GridView) findViewById(R.id.gv_image);

        listText = new ArrayList<>();
        listText.add("Bách");
        listText.add("Đức Anh");
        listText.add("Hải");
        listText.add("Phi");
        listText.add("Phong");
        listText.add("Boss");
        listText.add("Sơn");
        listText.add("Tân");
        listText.add("Thắng");
    }

    public void setAdapter()
    {
        imageAdapter = new ImageAdapter(this,R.layout.activity_custom_image,listText);
        gvImage.setAdapter(imageAdapter);
    }



}
