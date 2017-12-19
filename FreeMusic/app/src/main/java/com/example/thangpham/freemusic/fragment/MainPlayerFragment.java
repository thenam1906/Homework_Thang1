package com.example.thangpham.freemusic.fragment;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thangpham.freemusic.R;
import com.example.thangpham.freemusic.adapter.TopSongAdapter;
import com.example.thangpham.freemusic.databases.TopSongModel;
import com.example.thangpham.freemusic.events.OnClickTopSongEvent;
import com.example.thangpham.freemusic.utils.MusicHandler;
import com.squareup.picasso.Picasso;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListenerV1;
import com.thin.downloadmanager.RetryPolicy;
import com.thin.downloadmanager.ThinDownloadManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPlayerFragment extends Fragment {
    int downloadId;
    ArrayList<String> listName;
    private static final String TAG = "MainPlayerFragment";
    DownloadRequest downloadRequest;
    ImageView ivBack;
    ImageView ivLarge;
    ImageView ivDownload;
    ImageView ivPrevious;
    ImageView ivNext;
    ThinDownloadManager downloadManager;
    TextView tvSong;
    TextView tvSinger;
    TextView tvCurrent;
    TextView tvDuration;
     TopSongModel topSongModel;
    SeekBar sbTime;
    FloatingActionButton fab;
    public MainPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_main_player, container, false);
        setupUI(view);
        EventBus.getDefault().register(this);
        getListFile();
        checkDownload();
        return view;
    }



    public void setupUI(View view)
    {
        ivBack = view.findViewById(R.id.iv_back);
        ivLarge = view.findViewById(R.id.iv_largeImage);
        ivDownload = view.findViewById(R.id.iv_download);
        ivPrevious = view.findViewById(R.id.iv_previous);
        ivNext = view.findViewById(R.id.iv_next);

        tvSong= view.findViewById(R.id.tv_song);
        tvSinger= view.findViewById(R.id.tv_singer);
        tvCurrent= view.findViewById(R.id.tv_firstTime);
        tvDuration= view.findViewById(R.id.tv_lastTime);
        sbTime = view.findViewById(R.id.sb_time);
        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicHandler.playPause();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        downloadManager = new ThinDownloadManager();

        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MusicLoader musicLoader=new MusicLoader();
//                musicLoader.execute(topSongModel);
                downloadSong();
                downloadId = downloadManager.add(downloadRequest);
                ivDownload.setAlpha(50);
                ivDownload.setEnabled(false);
            }
        });
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TopSongModel topSongModelNext = new TopSongFragment().getTopSongModelNext(topSongModel);
                MusicHandler.getSearchSong(topSongModelNext,getContext());
                EventBus.getDefault().postSticky(new OnClickTopSongEvent(topSongModelNext));
            }
        });
        ivPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TopSongModel topSongModelPrevious = new TopSongFragment().getTopSongModelPrevious(topSongModel);
                MusicHandler.getSearchSong(topSongModelPrevious,getContext());
                EventBus.getDefault().postSticky(new OnClickTopSongEvent(topSongModelPrevious));
            }
        });
    }
    @Subscribe(sticky = true)
    public void onMiniPlayerClick(OnClickTopSongEvent onClickTopSongEvent)
    {
        topSongModel = onClickTopSongEvent.topSongModel;
        tvSong.setText(topSongModel.song);
        tvSinger.setText(topSongModel.singer);
        Log.d(TAG, "onMiniPlayerClick: "+topSongModel.song);
        Log.d(TAG, "onMiniPlayerClick: "+topSongModel.singer);
        Picasso.with(getContext()).load(topSongModel.largeImage).transform(new CropCircleTransformation()).into(ivLarge);

        MusicHandler.updateUIRealTime(getContext(),sbTime,fab,ivLarge,tvCurrent,tvDuration,topSongModel);
    }

    private void downloadSong() {


        String songName = topSongModel.song;
        String singerName = topSongModel.singer;
        String url = topSongModel.url;
        String fileName = songName +","+singerName;
        File file = getActivity().getExternalFilesDir("MusicDownload");

        Uri downloadUri = Uri.parse(url);
        Uri destinationUri = Uri.parse(file+"/"+fileName);
         downloadRequest = new DownloadRequest(downloadUri) // mô tả
                .setDestinationURI(destinationUri)
                .setDownloadContext("Download "+ fileName)
                .setStatusListener(new DownloadStatusListenerV1() {
                    @Override
                    public void onDownloadComplete(DownloadRequest downloadRequest) {
                        Toast.makeText(getContext(),downloadRequest.getDownloadContext()+" successfully!!" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDownloadFailed(DownloadRequest downloadRequest, int errorCode, String errorMessage) {
                        Toast.makeText(getContext(), "Download Error: "+errorCode+": "+errorMessage, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(DownloadRequest downloadRequest, long totalBytes, long downloadedBytes, int progress) {
//                        Toast.makeText(getContext(), "Downloading, Please wait...", Toast.LENGTH_SHORT).show();
                    }
                });
        getListFile();

    }
    public void getListFile()
    {
        String s="";
        File file = new File(getContext().getExternalFilesDir("MusicDownload").getPath());// mở file
        File listFile[] = file.listFiles();
         listName = new ArrayList<>();
        if(listFile.length==0)
        {
//            Toast.makeText(getContext(), "No Files Found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(File files: listFile)
            {

                listName.add(files.getName());
                s = s+ files.getName();
            }
        }

    }

    public void checkDownload()
    {
        String songName = topSongModel.song;
        String singerName = topSongModel.singer;
        String fileName = songName +","+singerName;

        boolean checkDownload = true;
        if(listName.size()!=0)
        {
            for(int i=0;i<listName.size();i++)
            {

                if(listName.get(i).equals(fileName))
                {
                    checkDownload=false;
                    break;
                }
                else
                    checkDownload=true;
            }
        }
        Log.d(TAG, "checkDownload: "+checkDownload);
        if(checkDownload==false)
        {
           // ivDownload.setVisibility(View.INVISIBLE);
            ivDownload.setAlpha(50);
            ivDownload.setEnabled(false);
        }
    }
    //    public class MusicLoader extends AsyncTask<TopSongModel,Void,String>
//    {
//
//
//        @Override
//        protected String doInBackground(TopSongModel... topSongModels) {
//            String nameFile = topSongModels[0].getSong()+", "+topSongModels[0].getSinger();
//            File file = getActivity().getExternalFilesDir("DownloadMusic");
//            try {
//                URL url = new URL(topSongModels[0].url);
//                InputStream input = url.openStream();
//
//                OutputStream outputStream = new FileOutputStream(file);
//                byte data[] = new byte[1024];
//                long total = 0;
//                int count=0;
//                while ((count = input.read(data)) != -1) {
//                    total += count;
//                    // writing data to file
//                    outputStream.write(data, 0, count);
//
//                }
//                // flushing output
//                outputStream.flush();
//
//                // closing streams
//                outputStream.close();
//                input.close();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }

}
