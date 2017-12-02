package com.example.thangpham.loginfb;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    LoginButton loginButton;
    TextView tv_name;
    ProfilePictureView profilePictureView;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        //// khi  gửi lên server 1 thông điệp thì server tra lai ta 1 message thong qua callbackManager qua onActivityResult...
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        setupUI();
        // gui len server 1 dang list ma thong tin muon xin nhu kieu email, profile, date, id....
        loginButton.setReadPermissions(Arrays.asList("public_profile"));
        setLoginFacebook();
    }

    private void setLoginFacebook() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            // khi đăng nhập thành công
            @Override
            public void onSuccess(LoginResult loginResult) {
                result();

            }
            // khi out
            @Override
            public void onCancel() {

            }
            // khi đăng nhập fail
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "Not sign in", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void result() {
        // GraphRequest giúp ta gửi yêu cầu lên server
        //AccessToken giup ta chung thuc viec dang nhap va dang xuat tren facebook
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {

                    profilePictureView.setProfileId(object.getString("id"));
                    tv_name.setText(object.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        // gửi dữ liệu lên server bằng Bundle
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,id");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    private void setupUI() {
        tv_name=findViewById(R.id.tv_name);
        profilePictureView = findViewById(R.id.imageProfilePictureView);
        loginButton= findViewById(R.id.login_button);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}
