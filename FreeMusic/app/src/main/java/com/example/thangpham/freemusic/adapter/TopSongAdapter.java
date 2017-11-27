package com.example.thangpham.freemusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thangpham.freemusic.R;
import com.example.thangpham.freemusic.databases.TopSongModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by ThangPham on 11/25/2017.
 */

public class TopSongAdapter extends RecyclerView.Adapter<TopSongAdapter.TopSongViewHolder> {
    Context context;
    public List<TopSongModel> topSongModelList;

    public TopSongAdapter(Context context, List<TopSongModel> topSongModelList) {
        this.context = context;
        this.topSongModelList = topSongModelList;
    }

    @Override
    public TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_top_song,parent,false);
        return new TopSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopSongViewHolder holder, int position) {
        holder.setData(topSongModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return topSongModelList.size();
    }


    public class TopSongViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivSong;
        TextView tvSong;
        TextView tvSinger;

        public TopSongViewHolder(View itemView) {
            super(itemView);
            ivSong = itemView.findViewById(R.id.iv_top_song);
            tvSong = itemView.findViewById(R.id.tv_song);
            tvSinger = itemView.findViewById(R.id.tv_singer);
        }
        public void setData(TopSongModel topSongModel)
        {
            Picasso.with(context).load(topSongModel.getSmallImage()).transform(new CropCircleTransformation()).into(ivSong);
            tvSong.setText(topSongModel.getSong());
            tvSinger.setText(topSongModel.getSinger());
        }
    }
}
