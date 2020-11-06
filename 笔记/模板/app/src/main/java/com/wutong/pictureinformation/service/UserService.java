package com.wutong.pictureinformation.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wutong.pictureinformation.entity.User;
import com.wutong.pictureinformation.util.MyDataBaseHelper;

public class UserService {
    private MyDataBaseHelper dbHelper;

    public UserService(Context context){
        dbHelper=new MyDataBaseHelper(context);
    }


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username,String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();//创建数据库
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});//rawQuery查询数据方法
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public boolean register(User user){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into user(username,password) values(?,?)";
        Object obj[]={user.getUsername(),user.getPassword()};
        sdb.execSQL(sql, obj);//execSQL添加更新 删除数据方法
        return true;
    }

}
