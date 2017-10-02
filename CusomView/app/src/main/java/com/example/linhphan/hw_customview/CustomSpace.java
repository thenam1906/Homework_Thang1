package com.example.linhphan.hw_customview;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Linh Phan on 9/29/2017.
 */

public class CustomSpace extends View {
    public CustomSpace(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(20);
        this.setMeasuredDimension(width, height);
    }
}
