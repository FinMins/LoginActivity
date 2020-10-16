package com.example.loginactivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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
         if(insertUser(usernum,userpassword)){
             Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
             getActivity().finish();
         };
                Toast.makeText(getContext(), "注册失败", Toast.LENGTH_SHORT).show();

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

    //用原生的sql语句插入用户
    private Boolean insertUser(String usernum,String userpassword){
        //如果这个账户没有被创建
         if(!isChecHavekUser(usernum)){
             UserDBHelper userDBHelper = new UserDBHelper(getContext(),"User",null,1);
             SQLiteDatabase db = userDBHelper.getWritableDatabase();
//             String INSERT_USER = "INSERT  INTO User values (2, \""+usernum+"\" ,\""+ userpassword+"\") ";
            //不写id看能不能自增
//             String INSERT_USER = "INSERT  INTO User(usernum,userpassword) values (\""+usernum+"\" ,\""+ userpassword+"\") ";

             //将id设置为null看行不行
             String INSERT_USER = "INSERT  INTO User values (null,\""+usernum+"\" ,\""+ userpassword+"\") ";


             Log.d("这是插入与", "执行插入语句");
             db.execSQL(INSERT_USER);
             return  true ;
         }
         return  false;
    }

    //用api插入用户
    private Boolean insertUserByApi(String usernum,String userpassword){
        //如果这个账户没有被创建
        if(!isChecHavekUserByAPI(usernum)){
            UserDBHelper userDBHelper = new UserDBHelper(getContext(),"User",null,1);
            SQLiteDatabase db = userDBHelper.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put("usernum",usernum);
          contentValues.put("userpassword",userpassword);
          db.insert("User",null,contentValues);
          contentValues.clear();
            return  true ;
        }
        return  false;
    }


    //用原生的sql语句判断用户是否已经注册
    private Boolean isChecHavekUser(String usernum){
        UserDBHelper userDBHelper = new UserDBHelper(getContext(),"User",null,1);
        SQLiteDatabase db = userDBHelper.getWritableDatabase();
        String SELETE_USER = "select usernum from User where usernum = \""+usernum+"\"";

        Cursor cursor =  db.rawQuery(SELETE_USER,null);

        if(cursor.moveToFirst()){
            cursor.close();
            Log.d("这是判断存在句子", "用户已存在");

            return true;
        }else {
            cursor.close();
            Log.d("这是判断存在句子", "用户不存在");

            return false;
        }
    }

    //用api方法判断用户是否已经注册
    private Boolean isChecHavekUserByAPI(String usernum){
        UserDBHelper userDBHelper = new UserDBHelper(getContext(),"User",null,1);
        SQLiteDatabase db = userDBHelper.getWritableDatabase();
//        String SELETE_USER = "select usernum from User ";
        ContentValues values = new ContentValues();
//            db.update("User",values,new String[] {""},);
        Cursor cursor =  db.query("User",null," usernum = \""+usernum+"\"", null,null,null,null   );
        if(cursor.moveToFirst()){
            cursor.close();
            return true;
        }else {
            cursor.close();
            return false;
        }
    }

}