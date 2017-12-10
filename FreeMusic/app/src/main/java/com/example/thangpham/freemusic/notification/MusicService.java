package com.example.thangpham.freemusic.notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.thangpham.freemusic.utils.MusicHandler;

/**
 * Created by ThangPham on 12/9/2017.
 */

public class MusicService extends Service {
    private static final String TAG = "MusicService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    // chay vao day service dc goi
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: "+"OK");
        MusicHandler.playPause();
        return super.onStartCommand(intent, flags, startId);
    }
    // check khi ng dùng kéo ra khỏi tab
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        MusicNotification.builder.setOngoing(false);
        MusicNotification.notificationManager.cancelAll(); // k cancle dc nhung thang`` trong trang thai OnGoing

    }
}
