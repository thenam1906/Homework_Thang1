package com.example.linhphan.maytinh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.*;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ArrayList<String> arrDau;
    ArrayList<Integer> arrSo;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

    private Button btnCong;
    private Button btnTru;
    private Button btnNhan;
    private Button btnChia;

    private Button btnC;
    private Button btnAC;
    private Button btnCham;
    private Button btnBang;

    private EditText edtNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maytinh_layout);
        initButton();
        setOnClickListenerAll();

    }
    void initButton()
    {
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btnCong = (Button) findViewById(R.id.btncong);
        btnTru = (Button) findViewById(R.id.btntru);
        btnNhan = (Button) findViewById(R.id.btnnhan);
        btnChia = (Button) findViewById(R.id.btnchia);

        btnC = (Button) findViewById(R.id.btnC);
        btnAC = (Button) findViewById(R.id.btnAC);
        btnCham = (Button) findViewById(R.id.btncham);
        btnBang = (Button) findViewById(R.id.btnbang);

        edtNumber = (EditText) findViewById(R.id.edt_input);
    }
    void setOnClickListenerAll()
    {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);

        btnC.setOnClickListener(this);
        btnAC.setOnClickListener(this);
        btnCham.setOnClickListener(this);
        btnBang.setOnClickListener(this);
    }
    public String deleteANummber(String s)
    {
        int length = s.length();
        String temp = s.substring(0,length-1);
        return temp;

    }
    public void controller()
    {
        // data = "1111+2222+333-1111"
        String data = edtNumber.getText().toString();
        for(int i=0;i<data.length();i++)
        {
            if(data.cha)
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn0:
            {
                edtNumber.append("0");
                break;
            }
            case R.id.btn1:
            {
                edtNumber.append("1");
                break;
            }
            case R.id.btn2:
            {
                edtNumber.append("2");
                break;
            }
            case R.id.btn3:
            {
                edtNumber.append("3");
                break;
            }
            case R.id.btn4:
            {
                edtNumber.append("4");
                break;
            }
            case R.id.btn5:
            {
                edtNumber.append("5");
                break;
            }
            case R.id.btn6:
            {
                edtNumber.append("6");
                break;
            }
            case R.id.btn7:
            {
                edtNumber.append("7");
                break;
            }
            case R.id.btn8:
            {
                edtNumber.append("8");
                break;
            }
            case R.id.btn9:
            {
                edtNumber.append("9");
                break;
            }
            //////////////////////////////////
            case R.id.btncong:
            {
                edtNumber.append("+");
                break;
            }
            case R.id.btntru:
            {
                edtNumber.append("-");
                break;
            }
            case R.id.btnnhan:
            {
                edtNumber.append("*");
                break;
            }
            case R.id.btnchia:
            {
                edtNumber.append("/");
                break;
            }
            case R.id.btnC:
            {
                edtNumber.setText(deleteANummber(edtNumber.getText().toString()));
                break;
            }
            case R.id.btnAC:
            {
                edtNumber.setText("");
                break;
            }
            case R.id.btncham:
            {
                edtNumber.append(".");
                break;
            }
            case R.id.btnbang:
            {
                edtNumber.append("/");
                break;
            }

        }

    }
}
