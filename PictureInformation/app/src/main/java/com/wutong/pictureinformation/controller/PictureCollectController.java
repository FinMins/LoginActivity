package com.wutong.pictureinformation.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.wutong.pictureinformation.entity.Picture;
import com.wutong.pictureinformation.entity.PictureCollect;
import com.wutong.pictureinformation.util.MyDataBaseHelper;

import java.util.ArrayList;
import java.util.List;


public class PictureCollectController {
    private MyDataBaseHelper dbHelper;
    private SQLiteDatabase sdb;

    public PictureCollectController(Context context) {
        dbHelper=new MyDataBaseHelper(context);
    }

    //收藏图片
    public boolean collectPicture(PictureCollect pictureCollect){
        sdb=dbHelper.getReadableDatabase();
        String sql="insert into picture_collect (url) values (?)";
        Object obj[]={pictureCollect.getUrl()};//将要详细地址url传入数据库，便可以依此打开新闻详显 "values(?)",new String[]{picture.getUrl()});
        sdb.execSQL(sql,obj);
        closeDb();
        return true;
    }

    //查询收藏图片
    public List<Picture> findCollect(String url){
        sdb=dbHelper.getReadableDatabase();
        String sql = "select * from picture_collect";
        Cursor cursor = sdb.rawQuery(sql, null);
        List<Picture> pictureList=new ArrayList<>();
        while(cursor.moveToNext()){
            Picture picture=new Picture();
            picture.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            pictureList.add(picture);
        }
        return pictureList;
    }

   /* //查询收藏图片
    public List<PictureCollect> findSelected(String url) {
        sdb = dbHelper.getReadableDatabase();
        String sql = "select * from picture_collect where url=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{url});
        List<PictureCollect> pictureCollectList = new ArrayList<>();
        while (cursor.moveToNext()) {
            PictureCollect pictureCollect = new PictureCollect();
            pictureCollect.setUrl(cursor.getString(cursor.getColumnIndex(url)));
            pictureCollectList.add(pictureCollect);
        }
        return pictureCollectList;
    }*/

    /**
     * 关闭数据库
     */
    public void closeDb(){
        if (sdb!=null){
            sdb.close();
        }
    }
}
