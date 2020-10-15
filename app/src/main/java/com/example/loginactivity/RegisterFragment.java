package com.example.loginactivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class RegisterFragment extends Fragment {
    private Button registerButton ;
    private EditText registerUserNum ;
    private EditText registerUserPassword;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();

        //监听注册按钮
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernum = getUserNum();
                String userpassword= getUserPassword();
                //判断是否已经注册，如果没有，则将数据写入数据库。

                //返回上一个
                getActivity().finish();
            }
        });



    }

    //初始化控件
    private void init(){
        registerButton = getView().findViewById(R.id.trueRegisterButton);
        registerUserNum = getView().findViewById(R.id.registerUserNum);
        registerUserPassword = getView().findViewById(R.id.registerUserPassword);
    }

    //获取用户输入的账户
    private String getUserNum(){
        return  registerUserNum.getText().toString();
    }

    //获取用户输入的密码
    private String getUserPassword(){
        return  registerUserPassword.getText().toString();
    }
}