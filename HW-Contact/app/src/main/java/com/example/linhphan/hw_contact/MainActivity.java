package com.example.linhphan.hw_contact;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    private ListView lvContact;
    private ArrayList<String> listName;
    private ArrayList<String> listPhone;
    private ArrayList<Integer> listImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        initAdapter();
        initPermission();
        addListener();
    }

    private void addListener() {
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel: "+listPhone.get(i)));
                startActivity(intent);
            }
        });
    }

    private void initPermission() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(checkSelfPermission(android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CALL_PHONE},1);
            }
        }
    }

    private void initAdapter() {
        ContactAdapter adapter = new ContactAdapter(this,listName,listPhone,listImage);
        lvContact.setAdapter(adapter);
    }
    
    private void setupUI() {
        lvContact= (ListView) findViewById(R.id.lv_contact);
        listName = new ArrayList<>();
        listName.add("Phạm Quang Thắng");
        listName.add("Tôn Ngộ Không");
        listName.add("Quách Tĩnh");
        listName.add("Trương Vô Kỵ");
        listName.add("Hoàng Dung");

        listPhone = new ArrayList<>();
        listPhone.add("01652000018");
        listPhone.add("09834343343");
        listPhone.add("09841023452");
        listPhone.add("02348123323");
        listPhone.add("01652319323");

        listImage= new ArrayList<>();
        listImage.add(R.drawable.male);
        listImage.add(R.drawable.female);
        listImage.add(R.drawable.male);
        listImage.add(R.drawable.male);
        listImage.add(R.drawable.female);
    }
    
}
