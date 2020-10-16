package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ShareActivity extends AppCompatActivity {
    private  String usernum  ; //用户账号


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        //获取传送过来的账号。
        getUserNum();


    }


    //获取传送过来的账号。
    private void getUserNum(){
        Intent intent = getIntent();
       usernum =  intent.getStringExtra("usernum");
    }

    //此函数用于碎片fragment获取用户的账号。
    public String getUsernumToFragment(){
        return usernum ;
    }
}