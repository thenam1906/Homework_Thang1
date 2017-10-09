package com.example.linhphan.hw_notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Linh Phan on 10/8/2017.
 */

public class DBManager extends SQLiteOpenHelper {
    private static final String TAG = "DBManager";
    public static String DATABASE_NAME = "ListNotePad";
    public static String TABLE = "NOTE";
    public static String TITLE = "title";
    public static String DESCRIPTION = "des";
    private Context context;

    // tao database
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    // tạo table trong database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: ");
        String sqlQuery = "CREATE TABLE NOTE (title text,des text );";
        sqLiteDatabase.execSQL(sqlQuery);

    }

    // khi có 1 sự thay đổi liên quan tới cấu trúc dữ liệu như 1table, or  xóa table
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXITS" + TABLE);
        onCreate(sqLiteDatabase);

    }

    // add
    public void addNotepad(NoteModel note) {
        // hàm getWritetableDatabase cho phép mình có thể mở DB và thay đổi dữ liệu trong đó
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues(); // phải dùng 1 đối tượng ContentValues để thêm dữ liệu vào bảng

        values.put(TITLE, note.getTitle());
        values.put(DESCRIPTION, note.getDescription());

        sqLiteDatabase.insert(TABLE, null, values);
        sqLiteDatabase.close();

    }

    public ArrayList<NoteModel> getAllNote() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NoteModel> listNote = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
  
        if (cursor != null) {
            Log.d(TAG, "getAllNote: Cursor !=null");
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Log.d(TAG, "getAllNote: "+cursor.getString(0));
                Log.d(TAG, "getAllNote: "+cursor.getString(1));
                listNote.add(new NoteModel(cursor.getString(0),cursor.getString(1)));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return listNote;
    }

}
