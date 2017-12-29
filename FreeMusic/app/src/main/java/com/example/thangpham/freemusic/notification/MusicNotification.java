package com.example.thangpham.freemusic.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.thangpham.freemusic.R;
import com.example.thangpham.freemusic.activity.MainActivity;
import com.example.thangpham.freemusic.databases.TopSongModel;
import com.example.thangpham.freemusic.utils.MusicHandler;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by ThangPham on 12/9/2017.
 */

public class MusicNotification {
    public static NotificationManager notificationManager;
    public static RemoteViews remoteViews;
    public static NotificationCompat.Builder builder;
    public static final String TAG = "MusicNotification";
    public  static final int NOTIFICATION_ID=1;

    public static void setupNotification(Context context, TopSongModel topSongModel)
    {
        remoteViews = new RemoteViews(context.getPackageName(),R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.tv_song,topSongModel.song);
        remoteViews.setTextViewText(R.id.tv_singer,topSongModel.singer);
        remoteViews.setImageViewResource(R.id.iv_play,R.drawable.ic_pause_black_24dp);

        Intent intent = new Intent(context, MainActivity.class);
        //PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
         builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews)
                .setContentIntent(pendingIntent) // sự kiện khi bấm vào builder
                .setOngoing(true);
        // quản lý noti ( quản lý việc, thông báo, update, add ,delete noti )
         notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Picasso.with(context).load(topSongModel.smallImage)
                .transform(new CropCircleTransformation())
                .into(remoteViews,R.id.iv_top_song,NOTIFICATION_ID,builder.build());
        setOnClickPlayPause(context);
//        updateNotification();
        notificationManager.notify(NOTIFICATION_ID, builder.build());// sau khi load anhr xong moi notifi


    }

    public static void updateNotification() {
        if(MusicHandler.hybridMediaPlayer.isPlaying())
        {
            remoteViews.setImageViewResource(R.id.iv_play,R.drawable.ic_pause_black_24dp);
            builder.setOngoing(true);
        }
        else
        {
            builder.setOngoing(false);
            remoteViews.setImageViewResource(R.id.iv_play,R.drawable.ic_play_arrow_black_24dp);
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public static void setOnClickPlayPause(Context context)
    {
        Intent intent = new Intent(context,MusicService.class);
        // service là 1 con chay ngầm, k cần giao diện
        PendingIntent pendingIntent = PendingIntent.getService(context,0,intent,0);
        remoteViews.setOnClickPendingIntent(R.id.iv_play,pendingIntent);

    }
}
