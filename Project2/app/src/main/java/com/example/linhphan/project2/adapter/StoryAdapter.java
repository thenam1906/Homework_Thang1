package com.example.linhphan.project2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linhphan.project2.R;
import com.example.linhphan.project2.databases.StoryModel;

import java.util.List;

/**
 * Created by Linh Phan on 10/7/2017.
 */

public class StoryAdapter extends ArrayAdapter<StoryModel> {
    Context context;
    int resource;
    List<StoryModel> storyModels;
    private static final String TAG = "StoryAdapter";
    public StoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<StoryModel> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.storyModels=objects;
    }
    // số lần getView đc gọi là số item hiện trên layout
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // setUI
        LayoutInflater layoutInflater = LayoutInflater.from(context); //
        convertView=layoutInflater.inflate(resource,parent,false);
        View vBookmark = convertView.findViewById(R.id.v_bookmark);
        TextView tvTitle= convertView.findViewById(R.id.tv_title); // convertView tự động ép kiểu cho mình
        TextView tvAuthor = convertView.findViewById(R.id.tv_author);
        ImageView imageView = convertView.findViewById(R.id.iv_story);
        //set data
        tvTitle.setText(storyModels.get(position).getTitle());
        tvAuthor.setText(storyModels.get(position).getAuthor());
        Log.d(TAG, "getView: "+position+" "+storyModels.get(position).getBookmark());
        if(storyModels.get(position).getBookmark())
        {
            vBookmark.setBackgroundColor(Color.RED);
        }
        else
            vBookmark.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        String[] base64 = storyModels.get(position).getImage().split(",");
        Bitmap bitmap = BitmapFactory.decodeByteArray(
                Base64.decode(base64[1],Base64.DEFAULT),
                0,// offset: vị trí bđ
                (Base64.decode(base64[1],Base64.DEFAULT)).length

        );
        imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
