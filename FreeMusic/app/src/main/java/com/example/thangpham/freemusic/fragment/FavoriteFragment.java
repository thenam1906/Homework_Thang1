package com.example.thangpham.freemusic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thangpham.freemusic.R;
import com.example.thangpham.freemusic.adapter.MusicTypeAdapter;
import com.example.thangpham.freemusic.databases.DatabaseHandler;
import com.example.thangpham.freemusic.databases.MusicTypeModel;
import com.example.thangpham.freemusic.events.OnUpdateRvFav;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    RecyclerView recyclerView;
    public static MusicTypeAdapter adapter;
    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView= view.findViewById(R.id.rv_favourite);
        adapter = new MusicTypeAdapter(DatabaseHandler.getFavourtie(),getContext());
        recyclerView.setAdapter(new MusicTypeAdapter(DatabaseHandler.getFavourtie(),getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        EventBus.getDefault().register(this);
        return view;
    }
    @Subscribe(sticky = true)
    public void update(OnUpdateRvFav onUpdateRvFav)
    {
        recyclerView.setAdapter(new MusicTypeAdapter(DatabaseHandler.getFavourtie(),getContext()));
    }

}
