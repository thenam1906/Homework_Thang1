package com.example.thangpham.freemusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thangpham.freemusic.R;
import com.example.thangpham.freemusic.activity.MainActivity;
import com.example.thangpham.freemusic.databases.MusicTypeModel;
import com.example.thangpham.freemusic.events.OnClickMusicTypeEvent;
import com.example.thangpham.freemusic.fragment.TopSongFragment;
import com.example.thangpham.freemusic.utils.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LINHPHAN on 11/18/2017.
 */

public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.MusicTypeViewHolder> {
    List<MusicTypeModel> musicTypeModelList;
    Context context;

    public MusicTypeAdapter(List<MusicTypeModel> musicTypeModelList,Context context) {
        this.musicTypeModelList = musicTypeModelList;
        this.context=context;
    }

    //create view
    @Override
    public MusicTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_music_type,parent,false);
        return new MusicTypeViewHolder(view);
    }
    //set data
    @Override
    public void onBindViewHolder(MusicTypeViewHolder holder, int position) {
        holder.setData(musicTypeModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return musicTypeModelList.size();
    }

    public class MusicTypeViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.iv_music_type) ImageView imageView;
        @BindView(R.id.tv_music_type) TextView textView;

        View view;
        public MusicTypeViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,itemView);
        }
        public void setData(final MusicTypeModel musicTypeMode){
           /// imageView.setImageResource(musicTypeMode.imageID);
            Picasso.with(context).load(musicTypeMode.imageID).into(imageView);
            textView.setText(musicTypeMode.key);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.openFragment(((MainActivity) context).getSupportFragmentManager(),R.id.rl_main,new TopSongFragment());
                    EventBus.getDefault().postSticky(new OnClickMusicTypeEvent(musicTypeMode));
                }
            });
        }
    }

}
