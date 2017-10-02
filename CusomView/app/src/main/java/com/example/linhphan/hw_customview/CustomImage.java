package com.example.linhphan.hw_customview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;


public class CustomImage extends android.support.v7.widget.AppCompatImageView {

    public CustomImage(Context context) {
        super(context);
        this.setScaleType(ScaleType.CENTER);
    }

    // được gọi để xác định yêu cầu kích thước cho view và các view con
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width,width/2);
        setLayoutParams(new LinearLayout.LayoutParams(width,width/2));
    }
}
