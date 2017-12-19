package com.example.thangpham.freemusic.utils;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thangpham.freemusic.R;
import com.example.thangpham.freemusic.databases.TopSongModel;
import com.example.thangpham.freemusic.events.OnClickTopSongEvent;
import com.example.thangpham.freemusic.fragment.TopSongFragment;
import com.example.thangpham.freemusic.network.MusicInterface;
import com.example.thangpham.freemusic.network.RetrofitInstance;
import com.example.thangpham.freemusic.network.SearchResponseJSON;
import com.example.thangpham.freemusic.notification.MusicNotification;

import org.greenrobot.eventbus.EventBus;

import java.util.logging.Handler;

import hybridmediaplayer.HybridMediaPlayer;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ThangPham on 11/29/2017.
 */

public class MusicHandler {
    public static HybridMediaPlayer hybridMediaPlayer;
    private static final String TAG = "MusicHandler";
    private static boolean keepUpdating = true;

    public static void getSearchSong(final TopSongModel topSongModel, final Context context) {
        MusicInterface musicInterface = RetrofitInstance.getInstance().create(MusicInterface.class);
        musicInterface.getSearchSong(topSongModel.song + " " + topSongModel.singer).enqueue(new Callback<SearchResponseJSON>() {
            @Override
            public void onResponse(Call<SearchResponseJSON> call, Response<SearchResponseJSON> response) {
                if (response.code() == 200) {
                    topSongModel.url = response.body().data.url;
                    topSongModel.largeImage = response.body().data.thumbnail;
                    playMusic(context, topSongModel);
                    MusicNotification.setupNotification(context,topSongModel);
                } else if (response.code() == 500) {
                    Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResponseJSON> call, Throwable t) {
                Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void playMusic(Context context, TopSongModel topSongModel) {
        // check xem đã nghe chưa, nếu nghe rồi thì dừng lại để bật bài khác
        if (hybridMediaPlayer != null) {
            hybridMediaPlayer.pause();
            hybridMediaPlayer.release();
        }
        hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
        hybridMediaPlayer.setDataSource(topSongModel.url);
        hybridMediaPlayer.prepare();  // đợi nó chuẩn bị
        // chuẩn bị xong xuôi sẽ nhảy vào hàm này
        hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                hybridMediaPlayer.play();

            }
        });

    }

    public static void playPause() {
        if (hybridMediaPlayer.isPlaying()) {
            hybridMediaPlayer.pause();
        } else
            hybridMediaPlayer.play();
        MusicNotification.updateNotification();
    }

    public static void updateUIRealTime(final Context context, final SeekBar seekBar, final FloatingActionButton floatingActionButton,
                                        final ImageView imageView, final TextView tvCurrent, final TextView tvDuration, final TopSongModel topSongModel) {
        final android.os.Handler handler = new android.os.Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //update UI
                if ( keepUpdating && hybridMediaPlayer != null)
                {
                    seekBar.setMax(hybridMediaPlayer.getDuration());
                    seekBar.setProgress(hybridMediaPlayer.getCurrentPosition());
                    if (hybridMediaPlayer.isPlaying()) {
                        floatingActionButton.setImageResource(R.drawable.ic_pause_black_24dp);
                    } else {
                        floatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    }
                    Utils.rotateImage(imageView, hybridMediaPlayer.isPlaying());

                    if(tvCurrent!=null)
                    {
                        tvCurrent.setText(Utils.convertTime(hybridMediaPlayer.getCurrentPosition()));
                        tvDuration.setText(Utils.convertTime(hybridMediaPlayer.getDuration()));
                    }
                   hybridMediaPlayer.setOnCompletionListener(new HybridMediaPlayer.OnCompletionListener() {
                       @Override
                       public void onCompletion(HybridMediaPlayer hybridMediaPlayer) {
                           TopSongModel topSongModelNext = new TopSongFragment().getTopSongModelNext(topSongModel);
                           MusicHandler.getSearchSong(topSongModelNext,context);
                           EventBus.getDefault().postSticky(new OnClickTopSongEvent(topSongModelNext));

                       }
                   });

                }
                handler.postDelayed(this, 100); // sau 100ml chay lai vao ham run
            }
        };
        runnable.run();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                keepUpdating = false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                hybridMediaPlayer.seekTo(seekBar.getProgress());
                keepUpdating = true;
            }
        });
    }
   

}
