package com.example.linhphan.hw_notepad;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    private ListView lvNotepad;
    private Button btOpen;
    private ArrayList<NoteModel> listNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        addListener();
        initAdapter();
    }

    private void initAdapter() {
        DBManager dbManager = new DBManager(this);
        listNote = dbManager.getAllNote();
        Log.d(TAG, "initAdapter: "+listNote);
        if (listNote!=null)
        {

            NoteAdapter adapter = new NoteAdapter(this,R.layout.item_list_notepad,listNote);
          //  adapter.notifyDataSetChanged();
            lvNotepad.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


    }

    private void addListener() {
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WriteNote.class);
                startActivity(intent);
            }
        });
        lvNotepad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initAlertDialog(position);
            }
        });
    }

    public void initAlertDialog(final int i)
    {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setTitle("Please select option");
        alBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NoteModel noteModel = listNote.get(i);
                DBManager.getInstance(MainActivity.this).deleteNodepad(noteModel);
                initAdapter();
            }
        });
        alBuilder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Update", Toast.LENGTH_SHORT).show();
            }
        });
        alBuilder.show();
    }

    private void setupUI() {
        lvNotepad = (ListView) findViewById(R.id.lv_notepad);
        btOpen = (Button) findViewById(R.id.bt_Open);
    }
}
