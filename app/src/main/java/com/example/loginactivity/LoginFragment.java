package com.example.loginactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;


public class LoginFragment extends Fragment {
private Button loginButton ; //登录按钮
    private Button registButton ; //注册按钮
    private RadioButton radioButton ; //记住密码按钮
    private EditText userNumEdit ; //输入账户控件。
    private EditText userPasswordEdit ;//输入密码控件

    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    //这里面不能加载控件，因为控件还未被初始化，要至少在onviewcreate（）里初始化控件。
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    //控件也不能再这里初始化
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    //从这个生命周期开始就能初始化控件了。
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();

        //点击登录按钮
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNum = getUserNum();
                String userPassword = getUserPassword();
                //点击后与数据库里的用户进行匹配。
                //未写

                //是否保存账号密码的操作
                rememberUser(userNum,userPassword);
            }
        });
        //点击注册按钮
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接跳转注册界面
                Intent intent = new Intent(getActivity(),RegistActivity.class);
                startActivity(intent);
            }
        });




    }


    //初始化控件
    private void init(){
        loginButton = getView().findViewById(R.id.loginButton);
        registButton = getView().findViewById(R.id.registButton);
        radioButton = getView().findViewById(R.id.radioButton);
        userNumEdit = getView().findViewById(R.id.loginUserEdit);
        userPasswordEdit = getView().findViewById(R.id.loginUserPassword);
        isRemember();
    }
    //判断有没有记住的账号密码
    private void isRemember(){
        if( getActivity().getSharedPreferences("isHaveRemberUser",0).getInt("isRemember",0)==1);
        {
            String usernum = getActivity().getSharedPreferences("isHaveRemberUser",0).getString("usernum","");
            String userpassword = getActivity().getSharedPreferences("isHaveRemberUser",0).getString("userpassword","");
            userNumEdit.setText(usernum);
            userPasswordEdit.setText(userpassword);
        }
    }


    //获取输入的用户名
    private String getUserNum(){
        return  userNumEdit.getText().toString();
    }
    //返回是否记住密码
    private Boolean getIsRemember(){
        return radioButton.isChecked();
    }
    //获取输入的密码
    private String getUserPassword(){
        return userPasswordEdit.getText().toString();
    }
        //记住密码
    private void rememberUser(String userNum,String userPassword){
        //如果记住密码
        if(getIsRemember()){
            SharedPreferences.Editor editor = getActivity().getSharedPreferences("isHaveRemberUser",0).edit();
            editor.putString("usernum",userNum);
            editor.putString("userpassword",userPassword);
        }
    }


}