package com.example.linhphan.project2.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Linh Phan on 10/7/2017.
 */

public class DatabaseHandle {
    private static final String TAG = DatabaseHandle.class.toString();
    private static DatabaseHandle databaseHandle;
    private AssetHelper assetHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHandle(Context context) {
        assetHelper = new AssetHelper(context);
    }

    public static DatabaseHandle getInstance(Context context) {
        if (databaseHandle == null) {
            databaseHandle = new DatabaseHandle(context);
            return databaseHandle;
        } else {
            return databaseHandle;
        }
    }

//    public void updateBookmark(StoryModel storyModel)
//    {
//        sqLiteDatabase =assetHelper.getWritableDatabase();
//        boolean bookmark = storyModel.getBookmark();
//        String title = storyModel.getTitle().trim();
//        String sql="";
//        if(bookmark)
//        {
//             sql = "update tbl_short_story set bookmark=0 where title='"+title+"' ";
//        }
//        else
//             sql = "update tbl_short_story set bookmark=1 where title='"+title+"' ";
//
//
//        sqLiteDatabase.execSQL(sql);
//        sqLiteDatabase.close();
//    }
    public void updateBookmark(StoryModel storyModel)
    {
        sqLiteDatabase =assetHelper.getWritableDatabase();
        boolean bookmark = storyModel.getBookmark();
        String title = storyModel.getTitle().trim();
        ContentValues value = new ContentValues();
        value.put("bookmark",!bookmark);
        sqLiteDatabase.update("tbl_short_story",value,"title=?",new String[]{title});
        sqLiteDatabase.close();
    }
    public List<StoryModel> getListStory() {
        List<StoryModel> storyModelList = new ArrayList<>();
        sqLiteDatabase = assetHelper.getReadableDatabase();
        // cursor be like ResultSet
        // sqLiteDatabase be like Statement
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tbl_short_story", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            // get data
            String image = cursor.getString(1);// so cot
            String title = cursor.getString(2);
            String description = cursor.getString(3);
            String content = cursor.getString(4);
            String author = cursor.getString(5);
            boolean bookmark = cursor.getInt(6) != 0; // tra ve true
            storyModelList.add(new StoryModel(image, title, description, content, author, bookmark));
            //next
            cursor.moveToNext();
        }
        Log.d(TAG, "getListStory: " + storyModelList.toString());
        return storyModelList;
    }
}
