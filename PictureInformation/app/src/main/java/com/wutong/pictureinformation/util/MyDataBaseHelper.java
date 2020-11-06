package com.wutong.pictureinformation.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public static String name="user.db";
    static int dbVersion=1;
    public static final String CREATE_USER="create table user(id integer primary key autoincrement,username varchar(20),password varchar(20))";
    public static final String CREATE_PICTURE_COLLECT="create table picture_collect(id integer primary key autoincrement,url varchar(50))";
    public static final String CREATE_ARTICLE_COLLECT="create table article_collect(id integer primary key autoincrement, title varchar(50),author varchar(10),publishedAt varchar(50))";

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, name, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("MyDataBaseHelper", "onCreate");
        db.execSQL(CREATE_PICTURE_COLLECT);
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_ARTICLE_COLLECT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("MyDataBaseHelper", "onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS picture_collect");
        db.execSQL("DROP TABLE IF EXISTS article_collect");
        onCreate(db);
    }
}
