package com.wutong.pictureinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.wutong.pictureinformation.controller.UserController;


public class LoginActivity extends AppCompatActivity {

    private SharedPreferences.Editor editor;
    private EditText etPhone, etPassword;
    private CheckBox cbRememberPassword;
    private Button btnLogin, btnRegister;
    String username, password,spPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        cbRememberPassword = findViewById(R.id.cb_remember_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etPhone.getText().toString();//获取输入框输入手机号
                password = etPassword.getText().toString();//获取输入框输入密码
                UserController userController=new UserController(LoginActivity.this);
                boolean flag=userController.login(username,password);
                if(flag){
                    Log.i("TAG","登录成功");
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Log.i("TAG","登录失败");
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                }

  /*              username = etPhone.getText().toString();//获取输入框输入手机号
                password = etPassword.getText().toString();//获取输入框输入密码
                spPassword = readPassword(username);//从SharedPreference中获取密码
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (password.equals(spPassword)) {//输入密码等于从SharedPreference中拿到的密码 登录成功
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);//跳转至主页面
                } else if (password != null && !TextUtils.isEmpty(password) && !password.equals(spPassword)) {//密码为空 或密码不等于注册时的密码
                    Log.d("LogActivity", password);
                    Toast.makeText(LoginActivity.this, "您输入的手机号或密码不正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "此手机号不存在", Toast.LENGTH_SHORT).show();//手机号不存在
                }*/
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

/*    *//**
     * 从SharedPreferences根据手机号读取密码
     * @param phone
     * @return
     *//*
    private String readPassword(String phone) {
        //getSharedPreferences("loginInfo",MODE_PRIVATE);
        //"loginInfo",mode_private; MODE_PRIVATE表示可以继续写入
        sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(phone, "");
    }*/
}