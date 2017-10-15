package com.example.linhphan.hw_notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UpdateActivity extends AppCompatActivity {
    public static final String TITLE="TITLE";
    public static final String DES="DES";
    public static final int RESULT_CODE=10;
    private TextView tvTitle;
    private EditText etDes;
    private Button btUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setupUI();
        receiveDataFromIntent();
        addListener();
    }

    private void addListener() {
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = tvTitle.getText().toString();
                String des = etDes.getText().toString();
                //update
                DBManager dbManager = DBManager.getInstance(UpdateActivity.this);
                dbManager.updateNotepad(new NoteModel(title,des));
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                intent.putExtra(DES,des);
                startActivity(intent);
                finish();
            }
        });
    }

    private void receiveDataFromIntent() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.TITLE);
        String des = intent.getStringExtra(MainActivity.DES);
        //set data
        tvTitle.setText(title);
        etDes.setText(des);

    }

    private void setupUI() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        etDes = (EditText) findViewById(R.id.et_description);
        btUpdate = (Button) findViewById(R.id.bt_UpdateNote);
    }
}
