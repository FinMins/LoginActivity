package com.wutong.pictureinformation.controller;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.wutong.pictureinformation.entity.User;
import com.wutong.pictureinformation.util.MyDataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private MyDataBaseHelper dbHelper;
    private SQLiteDatabase sdb;

    public UserController(Context context){
        dbHelper=new MyDataBaseHelper(context);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username,String password){
        sdb=dbHelper.getReadableDatabase();//创建数据库
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});//rawQuery查询数据方法
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        closeDb();
        return false;
    }

    //得到所有用户
    public List<User> getAllUsers(){
        sdb=dbHelper.getReadableDatabase();
        List<User> users=new ArrayList<>();
        String sql="select * from user";
        Cursor cursor=sdb.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                String username=cursor.getString(cursor.getColumnIndex("username"));
                String password=cursor.getString(cursor.getColumnIndex("password"));
                User user=new User();
                user.setUsername(username);
                user.setPassword(password);
                users.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public boolean register(User user){
        sdb=dbHelper.getReadableDatabase();
        String sql="insert into user(username,password) values(?,?)";
        Object obj[]={user.getUsername(),user.getPassword()};
        try {
            sdb.execSQL(sql, obj);//execSQL添加更新 删除数据方法
            closeDb();
        } catch (SQLException e) {
            /*e.printStackTrace();*/
            return false;
        }


     /*   ContentValues values=new ContentValues();
        values.put("username","ss");
        values.put("password","aa");
        sdb.insert("user",null,values);*/
        return true;
    }

    /**
     * 关闭数据库
     */
    public void closeDb(){
        if (sdb!=null){
            sdb.close();
        }
    }
}
