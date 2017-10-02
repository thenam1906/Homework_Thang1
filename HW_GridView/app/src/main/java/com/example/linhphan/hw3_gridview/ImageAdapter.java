package com.example.linhphan.hw3_gridview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Path;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Linh Phan on 10/1/2017.
 */

public class ImageAdapter extends BaseAdapter {

    private ArrayList<String> listName;
    private int a[]={R.drawable.anh1,R.drawable.anh2,R.drawable.anh3,R.drawable.anh4,R.drawable.anh5,
            R.drawable.anh6,R.drawable.anh7,R.drawable.anh8,R.drawable.anh9};
    private Context context;
    private int resource;
    public ImageAdapter(Context context, int resource, ArrayList<String> listName)
    {
        this.resource=resource;
        this.context=context;
        this.listName=listName;

    }
    @Override
    public View getView(int i, View converView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(converView==null)
        {
            viewHolder = new ViewHolder();
            converView = LayoutInflater.from(context).inflate(R.layout.activity_custom_image,viewGroup,false);
            viewHolder.imageView =(ImageView) converView.findViewById(R.id.iv_image);
            viewHolder.textView = (TextView) converView.findViewById(R.id.tv_text);

            converView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) converView.getTag();

        viewHolder.textView.setText(listName.get(i));
        viewHolder.imageView.setImageResource(a[i]);

        return converView;
    };

    @Override
    public int getCount() {
        return listName.size();
    }

    @Override
    public Object getItem(int i) {

        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder
    {
         ImageView imageView;
         TextView textView;
    }

}
