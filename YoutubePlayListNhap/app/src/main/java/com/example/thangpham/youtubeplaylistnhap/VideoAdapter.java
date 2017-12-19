package com.example.thangpham.youtubeplaylistnhap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ThangPham on 12/17/2017.
 */

public class VideoAdapter extends BaseAdapter {
    private static final String TAG = "VideoAdapter";
    public ArrayList<VideoInfo> listVideo;
    public int layout_id;
    public Context context;

    public VideoAdapter(ArrayList<VideoInfo> listVideo, int layout_id, Context context) {
        this.listVideo = listVideo;
        this.layout_id = layout_id;
        this.context = context;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: "+listVideo.size());
        return listVideo.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder=null;
        if(viewHolder==null)
        {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(layout_id,viewGroup,false);
            viewHolder.ivImage = view.findViewById(R.id.iv_image);
            viewHolder.tvSong = view.findViewById(R.id.tv_song);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvSong.setText(listVideo.get(i).getTitle());
        Picasso.with(context).load(listVideo.get(i).getUrl()).into(viewHolder.ivImage);
        return view;
    }
    class ViewHolder
    {
        ImageView ivImage;
        TextView tvSong;
    }
}
