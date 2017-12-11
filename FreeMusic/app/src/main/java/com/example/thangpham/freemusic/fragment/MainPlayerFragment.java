package com.example.thangpham.freemusic.fragment;


import android.net.Uri;
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
import java.io.IOException;
import java.io.InputStreamReader;
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
        tvSinger= view.findViewById(R.id.tv_song);
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
                downloadSong();
                downloadId = downloadManager.add(downloadRequest);
                ivDownload.setAlpha(50);
                ivDownload.setEnabled(false);
            }
        });
    }
    @Subscribe(sticky = true)
    public void onMiniPlayerClick(OnClickTopSongEvent onClickTopSongEvent)
    {
        topSongModel = onClickTopSongEvent.topSongModel;
        tvSong.setText(topSongModel.getSong());
        tvSinger.setText(topSongModel.getSinger());
        Picasso.with(getContext()).load(topSongModel.largeImage).transform(new CropCircleTransformation()).into(ivLarge);

        MusicHandler.updateUIRealTime(sbTime,fab,ivLarge,tvCurrent,tvDuration);
    }
    private void downloadSong() {

        //String url = topSongModel.url;

        String songName = topSongModel.song;
        String singerName = topSongModel.singer;
        String url = "https://tk-gx.herokuapp.com/api/audio?search_terms="+songName+" "+singerName+"";
        String fileName = songName +","+singerName;
        File file = getActivity().getExternalFilesDir("MusicDownload");

        Uri downloadUri = Uri.parse(url);
        Uri destinationUri = Uri.parse(file+"/"+fileName);
         downloadRequest = new DownloadRequest(downloadUri)
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
                        Toast.makeText(getContext(), "Downloading, Please wait...", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getContext(), "No Files Found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(File files: listFile)
            {

                listName.add(files.getName());
                s = s+ files.getName();
            }
        }
        Log.d(TAG, "getListFile: "+listFile.length);
        Log.d(TAG, "getListFile: "+s+"-");

    }
    public void readFile()
    {
        String nameFiles="";
        File file;
         file = new File(getContext().getExternalFilesDir("MusicDownload").getPath());// mở file
        BufferedReader readFile=null;
        try {
            readFile = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while(readFile.readLine()!=null)
            {
                nameFiles+= readFile.readLine()+"\r\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "readFile: "+nameFiles);
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
}
