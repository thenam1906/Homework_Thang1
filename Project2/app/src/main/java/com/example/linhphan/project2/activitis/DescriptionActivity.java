package com.example.linhphan.project2.activitis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linhphan.project2.R;
import com.example.linhphan.project2.databases.DatabaseHandle;
import com.example.linhphan.project2.databases.StoryModel;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivStory;
    private ImageView ivBack;
    private ImageView ivBookmark;
    private TextView tvAuthor;
    private TextView tvTitle;
    private TextView tvDes;
    private Button btStartReading;
    public static boolean check=false;
    public static int colorBookmark;
    DatabaseHandle databaseHandle;
    StoryModel storyModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        setupUI();
        ađdListener();
        loadData();
        databaseHandle=DatabaseHandle.getInstance(this);
    }

    private void loadData() {
        storyModel = (StoryModel) getIntent().getSerializableExtra(MainActivity.STORY_KEY);
        String[] base64 = storyModel.getImage().split(",");
        Bitmap bitmap = BitmapFactory.decodeByteArray(
                Base64.decode(base64[1],Base64.DEFAULT),
                0,// offset: vị trí bđ
                (Base64.decode(base64[1],Base64.DEFAULT)).length

        );
        ivStory.setImageBitmap(bitmap);
        tvTitle.setText(storyModel.getTitle());
        tvAuthor.setText(storyModel.getAuthor());
        tvDes.setText(storyModel.getDescription());
        if(storyModel.getBookmark())
        {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_red_24dp);
        }
        else
        {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
        }
    }

    private void ađdListener() {
        ivBack.setOnClickListener(this);
        ivBookmark.setOnClickListener(this);
        btStartReading.setOnClickListener(this);
    }

    private void setupUI() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBookmark = (ImageView) findViewById(R.id.iv_bookmark);
        ivStory= (ImageView) findViewById(R.id.iv_story);
        tvTitle= (TextView) findViewById(R.id.tv_title);
        tvAuthor= (TextView) findViewById(R.id.tv_author);
        tvDes= (TextView) findViewById(R.id.tv_des);
        btStartReading= (Button) findViewById(R.id.bt_start_reading);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
            {
                super.onBackPressed(); // giong nut back tren man hinh
                break;
            }
            case R.id.iv_bookmark:
            {
                changeColorBookmark();
                break;
            }
            case R.id.bt_start_reading:
            {
                break;
            }
        }
    }
    public void changeColorBookmark()
    {
        if(!storyModel.getBookmark())
        {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_red_24dp);
            databaseHandle.updateBookmark(storyModel);
        }
        else
        {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
            databaseHandle.updateBookmark(storyModel);
        }
    }

}
