package com.example.thangpham.youtubeplaylistnhap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TabStopSpan;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayListActivity extends AppCompatActivity {
    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
//        youTubePlayerView= findViewById(R.id.myVideo);
//        Intent intent = getIntent();
//        final String videoId = intent.getStringExtra("videoId");
//        youTubePlayerView.initialize(MainActivity.API_KEY, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo(videoId);
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Toast.makeText(PlayListActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
