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
import com.example.thangpham.freemusic.events.OnClickTopSongEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by ThangPham on 12/10/2017.
 */

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.TopSongViewHolder>{
    Context context;
    View view;
    public List<TopSongModel> topSongModelList;

    public DownloadAdapter(Context context, List<TopSongModel> topSongModelList) {
        this.context = context;
        this.topSongModelList = topSongModelList;
    }

    @Override
    public DownloadAdapter.TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_top_song,parent,false);
        return new DownloadAdapter.TopSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DownloadAdapter.TopSongViewHolder holder, int position) {
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
            view=itemView;
        }
        public void setData(final TopSongModel topSongModel)
        {
           Picasso.with(context).load(topSongModel.smallImage).transform(new CropCircleTransformation()).into(ivSong);
            tvSong.setText(topSongModel.getSong());
            tvSinger.setText(topSongModel.getSinger());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().postSticky(new OnClickTopSongEvent(topSongModel));

                }
            });
        }
    }
}
