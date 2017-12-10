package com.example.thangpham.freemusic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thangpham.freemusic.R;
import com.example.thangpham.freemusic.adapter.DownloadAdapter;
import com.example.thangpham.freemusic.adapter.TopSongAdapter;
import com.example.thangpham.freemusic.databases.TopSongModel;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {
    private static final String TAG = "DownloadFragment";
    RecyclerView rvListDownload;
    ArrayList<String> listName;
    ArrayList<TopSongModel> listTopSongModel;
    public DownloadFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        setupUI(view);
        getListFile();
        DownloadAdapter adapter = new DownloadAdapter(getContext(),listTopSongModel);
        rvListDownload.setLayoutManager(new LinearLayoutManager(getContext()));
        rvListDownload.setAdapter(adapter);
        return view;
    }

    private void setupUI(View view) {
        listTopSongModel = new ArrayList<>();
        rvListDownload = view.findViewById(R.id.rv_listDownload);
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
                TopSongModel topSongModel = new TopSongModel();
                topSongModel.song=getSongName(files.getName());
                topSongModel.singer=getSingerName(files.getName());
                topSongModel.smallImage="https://scontent.fhan5-2.fna.fbcdn.net/v/t1.0-9/20597164_1386705108111399_2471613987463837414_n.jpg?oh=1f6cd3e499482229b47d0a8b05c7a5ea&oe=5AD7B2A8";
                listTopSongModel.add(topSongModel);
                Log.d(TAG, "getListFile: "+file.getAbsolutePath());
            }
        }
    }
    public String getSongName(String fileName)
    {
        String [] s = fileName.split(",");
        return s[0];
    }
    public String getSingerName(String fileName)
    {
        String [] s = fileName.split(",");
        return s[1];
    }
}
