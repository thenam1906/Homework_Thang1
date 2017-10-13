package com.example.linhphan.project2.activitis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.linhphan.project2.databases.DatabaseHandle;
import com.example.linhphan.project2.R;
import com.example.linhphan.project2.adapter.StoryAdapter;
import com.example.linhphan.project2.databases.StoryModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String STORY_KEY="story_key";
    private List<StoryModel> storyModelList=new ArrayList<>();
    private ListView lvStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storyModelList = DatabaseHandle.getInstance(this).getListStory();
        lvStory =(ListView) findViewById(R.id.lv_store);
        StoryAdapter storyAdapter = new StoryAdapter(this,R.layout.item_list_story,storyModelList);
        lvStory.setAdapter(storyAdapter);
        lvStory.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this,DescriptionActivity.class);
        intent.putExtra(STORY_KEY,storyModelList.get(position));
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        storyModelList = DatabaseHandle.getInstance(this).getListStory();
        lvStory =(ListView) findViewById(R.id.lv_store);
        StoryAdapter storyAdapter = new StoryAdapter(this,R.layout.item_list_story,storyModelList);
        lvStory.setAdapter(storyAdapter);
        lvStory.setOnItemClickListener(this);
    }
}
