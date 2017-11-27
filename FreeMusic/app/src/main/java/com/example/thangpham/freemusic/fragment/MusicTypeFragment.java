package com.example.thangpham.freemusic.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thangpham.freemusic.R;
import com.example.thangpham.freemusic.adapter.MusicTypeAdapter;
import com.example.thangpham.freemusic.databases.MusicTypeModel;
import com.example.thangpham.freemusic.network.MusicTypeResponseJSON;
import com.example.thangpham.freemusic.network.MusicInterface;
import com.example.thangpham.freemusic.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
// recycle view phai co LayoutManagement va Adapter
public class MusicTypeFragment extends Fragment {
    private Context context;
    MusicTypeAdapter musicTypeAdapter;
    @BindView(R.id.rv_musictype)
    RecyclerView rvMusicType;
    private List<MusicTypeModel> musicTypeModelList = new ArrayList<>();
    public MusicTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_type, container, false);
        context = getContext();
        ButterKnife.bind(this,view);
         musicTypeAdapter = new MusicTypeAdapter(musicTypeModelList,context);
        rvMusicType.setAdapter(musicTypeAdapter);

        // rvMusicType.setLayoutManager(new LinearLayoutManager(context));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return (position % 3 == 0 ? 2 : 1) ;
            }
        });
        rvMusicType.setLayoutManager(gridLayoutManager);
        loadData();
        return view;
    }
    private void loadData()
    {
        MusicInterface musicTypesInterface =
                RetrofitInstance.getInstance().create(MusicInterface.class); // đính kèm con interface vào introfit;
        musicTypesInterface.getMusicTypes().enqueue(new Callback<MusicTypeResponseJSON>() {
            @Override
            public void onResponse(Call<MusicTypeResponseJSON> call, Response<MusicTypeResponseJSON> response) {
                List<MusicTypeResponseJSON.SubObjectJSON> list = response.body().subgenres;
                for(int i=0;i< list.size();i++)
                    {
                    MusicTypeModel musicTypeModel = new MusicTypeModel();
                    musicTypeModel.id=list.get(i).id;
                    musicTypeModel.key=list.get(i).translation_key;
                    musicTypeModel.imageID =context.getResources().getIdentifier(
                            "genre_x2_"+musicTypeModel.id,
                            "raw",
                            context.getPackageName()
                    );
                    musicTypeModelList.add(musicTypeModel);
                }
                musicTypeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MusicTypeResponseJSON> call, Throwable t) {
                Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
