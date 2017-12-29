package com.example.thangpham.freemusic.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thangpham.freemusic.R;
import com.example.thangpham.freemusic.adapter.TopSongAdapter;
import com.example.thangpham.freemusic.databases.DatabaseHandler;
import com.example.thangpham.freemusic.databases.MusicTypeModel;
import com.example.thangpham.freemusic.databases.TopSongModel;
import com.example.thangpham.freemusic.events.OnClickMusicTypeEvent;
import com.example.thangpham.freemusic.events.OnUpdateRvFav;
import com.example.thangpham.freemusic.network.MusicInterface;
import com.example.thangpham.freemusic.network.RetrofitInstance;
import com.example.thangpham.freemusic.network.TopSongResponseJSON;
import com.google.android.exoplayer2.util.ColorParser;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment {
    private static final String TAG = "TopSongFragment";
    @BindView(R.id.avLoad)
    AVLoadingIndicatorView avLoad;
    @BindView(R.id.tv_music_type)
    TextView tvMusicType;
    @BindView(R.id.iv_favourite)
    ImageView ivFavourite;
    @BindView(R.id.iv_music_type)
    ImageView ivMusicType;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rv_top_songs)
    RecyclerView rvTopSongs;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    public TopSongAdapter topSongAdapter;
    public static List<TopSongModel> topSongModelList = new ArrayList<>();
    public MusicTypeModel musicTypeModel;
    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_top_song, container, false);
        EventBus.getDefault().register(this);

        setupUI(view);
        loadData();
        return view;
    }
    public void loadData()
    {
        topSongModelList.clear();
        MusicInterface musicInterface = RetrofitInstance.getInstance().create(MusicInterface.class);
        musicInterface.getTopSong(musicTypeModel.id)
                .enqueue(new Callback<TopSongResponseJSON>() {
                    @Override
                    public void onResponse(Call<TopSongResponseJSON> call, Response<TopSongResponseJSON> response) {
                        avLoad.hide();
                        List<TopSongResponseJSON.FeedJSON.Entry> entryJSONList = response.body().feed.entry;
                        for(int i=0;i<entryJSONList.size();i++)
                        {
                            TopSongModel topSongModel = new TopSongModel();
                            topSongModel.singer = entryJSONList.get(i).artist.label;
                            topSongModel.song = entryJSONList.get(i).name.label;
                            topSongModel.smallImage = entryJSONList.get(i).image.get(2).label;

                            topSongModelList.add(topSongModel);
                            topSongAdapter.notifyItemChanged(i);
                        }
//                        topSongAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<TopSongResponseJSON> call, Throwable t) {
                        Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: "+t.toString());
                    }
                });

    }
    // chỉ ra rằng bạn muốn nhận một sticky event từ bộ nhớ đệm ( EventBus)
    @Subscribe(sticky = true)
    public void onReceivedOnMusicTypeClicked(OnClickMusicTypeEvent onClickMusicTypeEvent)
    {
        musicTypeModel = onClickMusicTypeEvent.musicTypeModel;
    }
    private void setupUI(View view) {
        ButterKnife.bind(this,view);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        tvMusicType.setText(musicTypeModel.key);
        Picasso.with(getContext()).load(musicTypeModel.imageID).into(ivMusicType);
            // khi keo xuong se nhay vao ham nay
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "onOffsetChanged: "+verticalOffset);
                if(verticalOffset==0)
                {
                    toolbar.setBackground(getResources().getDrawable(R.drawable.custom_gradient_text_bot_to_top));
                }
                else
                {
                 toolbar.setBackground(null);
                }
         }});
        topSongAdapter = new TopSongAdapter(getContext(),topSongModelList);
        rvTopSongs.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTopSongs.setAdapter(topSongAdapter);

        rvTopSongs.setItemAnimator(new SlideInLeftAnimator());
        rvTopSongs.getItemAnimator().setAddDuration(300);
        avLoad.show();

        if(musicTypeModel.isFavorite)
        {
            ivFavourite.setColorFilter(Color.RED);
        }
        else
        {
            ivFavourite.setColorFilter(Color.WHITE);
        }

        ivFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler.updateFavourtie(musicTypeModel);
                if(musicTypeModel.isFavorite)
                {
                    ivFavourite.setColorFilter(Color.RED);
                }
                else
                {
                    ivFavourite.setColorFilter(Color.WHITE);
                }
                EventBus.getDefault().postSticky(new OnUpdateRvFav());
               // FavoriteFragment.adapter.notifyDataSetChanged();
            }
        });
    }
    public static TopSongModel getTopSongModelNext(TopSongModel topSongModel)
    {
        TopSongModel topSongModeNext=topSongModel;
        for(int i=0;i<topSongModelList.size();i++)
        {
            if(topSongModel.getSong().equals(topSongModelList.get(i).getSong())&&i!=topSongModelList.size()-1)
            {
                topSongModeNext=topSongModelList.get(i+1);
                break;
            }
        }
        return topSongModeNext;
    }
    public static TopSongModel getTopSongModelPrevious(TopSongModel topSongModel)
    {
        TopSongModel topSongModePrevious=topSongModel;
        for(int i=0;i<topSongModelList.size();i++)
        {
            if(topSongModel.getSong().equals(topSongModelList.get(i).getSong())&&i!=0)
            {
                topSongModePrevious=topSongModelList.get(i-1);
                break;
            }
        }
        return topSongModePrevious;
    }
}
