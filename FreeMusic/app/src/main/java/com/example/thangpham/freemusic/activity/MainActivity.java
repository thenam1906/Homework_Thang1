package com.example.thangpham.freemusic.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.thangpham.freemusic.fragment.MainPlayerFragment;
import com.example.thangpham.freemusic.utils.MusicHandler;
import com.example.thangpham.freemusic.adapter.ViewPagerAdapter;
import com.example.thangpham.freemusic.R;
import com.example.thangpham.freemusic.databases.TopSongModel;
import com.example.thangpham.freemusic.events.OnClickTopSongEvent;
import com.example.thangpham.freemusic.utils.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.v_pager)
    ViewPager viewPager;
    TopSongModel topSongModel;

    @BindView(R.id.rl_mini)
    RelativeLayout rlMini;

    @BindView(R.id.sb_mini)
    SeekBar seekBar;
    @BindView(R.id.iv_top_song)
    ImageView ivTopSong;
    @BindView(R.id.tv_song)
    TextView tvSong;
    @BindView(R.id.tv_singer)
    TextView tvSinger;
    @BindView(R.id.fb_mini)
    FloatingActionButton fbPlay;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            EventBus.getDefault().register(this);
            setupUI();
    }
    @Subscribe(sticky = true)
    public void onRecivedTopSong(OnClickTopSongEvent onClickTopSongEvent)
    {
        Log.d(TAG, "onRecivedTopSong: OK");
        topSongModel = onClickTopSongEvent.topSongModel;
        rlMini.setVisibility(View.VISIBLE);
        Picasso.with(this).load(topSongModel.getSmallImage()).transform(new CropCircleTransformation()).into(ivTopSong);
        tvSong.setText(topSongModel.getSong());
        tvSinger.setText(topSongModel.getSinger());
        MusicHandler.getSearchSong(topSongModel,MainActivity.this);
        MusicHandler.updateUIRealTime(this,seekBar,fbPlay,ivTopSong,null,null,topSongModel);

    }
    private void setupUI() {
        ButterKnife.bind(this); //

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_dashboard_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_file_download_black_24dp));
        tabLayout.getTabAt(0).getIcon().setAlpha(255);
        tabLayout.getTabAt(1).getIcon().setAlpha(100);
        tabLayout.getTabAt(2).getIcon().setAlpha(100);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            // chọn r muốn chọn tiếp
            }


        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        seekBar.setPadding(0,0,0,0);

        fbPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicHandler.playPause();
            }
        });

        rlMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openFragment(getSupportFragmentManager(),R.id.rl_main_player,new MainPlayerFragment());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: "+getSupportFragmentManager().getBackStackEntryCount());
        if(getSupportFragmentManager().getBackStackEntryCount()!=0) // xem backStack co bao nhieu con fragment
        {
            super.onBackPressed();
        }
        else
            moveTaskToBack(true); // nhu bam nut home

    }
}
