package com.example.linhphan.hw_notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteNote extends AppCompatActivity {
    private static final String TAG = "WriteNote";
    private EditText etTitle;
    private EditText etDescription;
    private Button btAddNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        setupUI();
        addListener();
    }

    private void addListener() {
        btAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
                NoteModel noteModel = new NoteModel(title,description);

                DBManager db = new DBManager(WriteNote.this);
                db.addNotepad(noteModel);
                Toast.makeText(WriteNote.this, "Add Notepad successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WriteNote.this,MainActivity.class);
                startActivity(intent);
                //finish();

            }
        });
    }


    private void setupUI() {
        etTitle= (EditText) findViewById(R.id.et_title);
        etDescription= (EditText) findViewById(R.id.et_description);
        btAddNote= (Button) findViewById(R.id.bt_AddNote);
    }

}
