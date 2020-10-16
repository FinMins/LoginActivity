package com.example.loginactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;


public class LoginFragment extends Fragment {
private Button loginButton ; //登录按钮
    private Button registButton ; //注册按钮
    private RadioButton radioButton ; //记住密码按钮
    private EditText userNumEdit ; //输入账户控件。
    private EditText userPasswordEdit ;//输入密码控件
    private Boolean  IS_CHECKED =true ;
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

        //这里是主事件逻辑处
        //点击登录按钮
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNum = getUserNum();
                String userPassword = getUserPassword();
                //点击后与数据库里的用户进行匹配。
                if(selectUser(userNum,userPassword)){
                    //是否保存账号密码的操作
                    rememberUser(userNum,userPassword);
                    Intent intent = new Intent(getContext(),ShareActivity.class);
                    intent.putExtra("usernum",userNum);
                    startActivity(intent);
                }
                else Toast.makeText(getContext(), "密码错误", Toast.LENGTH_SHORT).show();
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

        //设解决单选框的重复点击只能时true的bug。
    radioButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            radioButton.setChecked(IS_CHECKED);
            IS_CHECKED=!IS_CHECKED;
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
        if( getActivity().getSharedPreferences("isHaveRemberUser", Context.MODE_PRIVATE).getInt("isRemember",0)==1);
        {
            String usernum = getActivity().getSharedPreferences("isHaveRemberUser",0).getString("usernum","");
            String userpassword = getActivity().getSharedPreferences("isHaveRemberUser",0).getString("userpassword","");
            userNumEdit.setText(usernum);
            userPasswordEdit.setText(userpassword);
//            Toast.makeText(getContext(), usernum+"+"+userpassword, Toast.LENGTH_SHORT).show();
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
            SharedPreferences.Editor editor = getActivity().getSharedPreferences("isHaveRemberUser",Context.MODE_PRIVATE).edit();
            editor.putString("usernum",userNum);
            editor.putString("userpassword",userPassword);
            editor.apply();//这里忘记申请了
//            Toast.makeText(getContext(), "账号密码保存成功"+userNum+"/"+userPassword, Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences.Editor editor = getActivity().getSharedPreferences("isHaveRemberUser",Context.MODE_PRIVATE).edit();
            editor.putString("usernum","");
            editor.putString("userpassword","");
            editor.apply();//这里忘记申请了
            //Toast.makeText(getContext(), "账号密码保存成功"+userNum+"/"+userPassword, Toast.LENGTH_SHORT).show();
        }
    }


    //用原生的sql语句判断用户账号密码是否正确。
    private Boolean selectUser(String usernum,String userpassword){
        UserDBHelper userDBHelper = new UserDBHelper(getContext(),"User",null,1);
        SQLiteDatabase db = userDBHelper.getWritableDatabase();
        String SELECT_USER = "select  userpassword  from User Where usernum = \""+usernum+"\"";
        Cursor cursor =  db.rawQuery(SELECT_USER,null);
        if( cursor.moveToFirst()){
            //第一条数据就是用户的数据
            String tem_userpassword = cursor.getString(cursor.getColumnIndex("userpassword"));
            if (userpassword.equals(tem_userpassword))
            {
                Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                return true;
            }else{
                Toast.makeText(getContext(), "密码错误", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        Toast.makeText(getContext(), "账号未注册", Toast.LENGTH_SHORT).show();
        return  false ;
    }


}