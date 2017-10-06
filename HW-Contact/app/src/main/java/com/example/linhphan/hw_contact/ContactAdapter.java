package com.example.linhphan.hw_contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Linh Phan on 10/6/2017.
 */

public class ContactAdapter extends BaseAdapter {
    ArrayList<String> listName;
    ArrayList<String> listPhone;
    ArrayList<Integer> listImage;
    Context context;

    public ContactAdapter(Context context,ArrayList<String> listName, ArrayList<String> listPhone,ArrayList<Integer> listImage ) {
        this.listName = listName;
        this.listPhone = listPhone;
        this.listImage=listImage;
        this.context = context;
    }

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

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder= new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_contact,viewGroup,false);
            viewHolder.tvName=(TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvPhone=(TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.ivImage=(ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.tvName.setText(listName.get(i));
            viewHolder.tvPhone.setText(listPhone.get(i));
            viewHolder.ivImage.setImageResource(listImage.get(i));
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        return convertView;
    }
    public class ViewHolder
    {
        TextView tvPhone;
        TextView tvName;
        ImageView ivImage;
    }
}
