package com.wutong.pictureinformation.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wutong.pictureinformation.entity.Article;
import com.wutong.pictureinformation.entity.ArticleCollect;
import com.wutong.pictureinformation.util.MyDataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ArticleCollectController {

    private MyDataBaseHelper dbHelper;
    private SQLiteDatabase sdb;

    public ArticleCollectController(Context context) {
        dbHelper=new MyDataBaseHelper(context);
    }

    //收藏文章
    public boolean collectArticle(ArticleCollect articleCollect){
        sdb=dbHelper.getReadableDatabase();
        String sql="insert into picture_collect (title,author,publishedAt) values (?,?,?)";
        Object obj[]={articleCollect.getTitle(),articleCollect.getAuthor(),articleCollect.getPublishedAt()};//将要详细地址url传入数据库，便可以依此打开新闻详显 "values(?)",new String[]{picture.getUrl()});
        sdb.execSQL(sql,obj);
        closeDb();
        return true;
    }

    //查询收藏文章
    public List<Article> findCollect(String url){
        sdb=dbHelper.getReadableDatabase();
        String sql = "select * from article_collect";
        Cursor cursor = sdb.rawQuery(sql, null);
        List<Article> articleList=new ArrayList<>();
        while(cursor.moveToNext()){
            Article article=new Article();
            article.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
            article.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            article.setPublishedAt(cursor.getString(cursor.getColumnIndex("publishedAt")));
            articleList.add(article);
        }
        return articleList ;
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
