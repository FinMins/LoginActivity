package com.wutong.pictureinformation.util;

import android.util.Log;


import com.wutong.pictureinformation.entity.Article;
import com.wutong.pictureinformation.entity.Picture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJsonWithJsonObject {


    /**
     * 解析图片
     * @param jsonData
     * @return
     */
    public static List<Picture> getPictures(String jsonData) {
        List<Picture> pictures=new ArrayList<>();
        try {
            JSONObject json=new JSONObject(jsonData);
            JSONArray data = json.getJSONArray("data");//将服务器返回数据传入jsonArray中
            for (int i=0;i<data.length();i++){
                Picture picture=new Picture();
                picture.setId(data.getJSONObject(i).getString("_id"));
                picture.setUrl(data.getJSONObject(i).getString("url"));
                picture.setType(data.getJSONObject(i).getString("type"));
                picture.setCategory(data.getJSONObject(i).getString("category"));
                picture.setLikeCounts(data.getJSONObject(i).getInt("likeCounts"));
                Log.d("getPictures",picture.getId());
                Log.d("getPictures",picture.getUrl());
                Log.d("getPictures",picture.getType());
                Log.d("getPictures",picture.getCategory());
                Log.d("getPictures",String.valueOf(picture.getLikeCounts()));
                pictures.add(picture);
            }
            return pictures;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解析文章
     * @param jsonData
     * @return
     */
    public static List<Article> getArticles(String jsonData) {
        List<Article> articles=new ArrayList<>();
        try {
            if(jsonData.startsWith("\ufeff")){
                jsonData= jsonData.substring(1);
            }
            JSONObject json=new JSONObject(jsonData);
            JSONArray data = json.getJSONArray("data");//将服务器返回数据传入jsonArray中
            for (int i=0;i<data.length();i++){
                Article article=new Article();
                article.setId(data.getJSONObject(i).getString("_id"));
                article.setTitle(data.getJSONObject(i).getString("title"));
                article.setAuthor(data.getJSONObject(i).getString("author"));
                article.setPublishedAt(data.getJSONObject(i).getString("publishedAt"));
                article.setDesc(data.getJSONObject(i).getString("desc"));
                article.setContent(data.getJSONObject(i).getString("url"));
                Log.d("getArticles",article.getTitle());
                Log.d("getArticles",article.getAuthor());
                Log.d("getArticles",article.getPublishedAt());
                Log.d("getArticles",article.getDesc());
                Log.d("getArticles",article.getContent());
                articles.add(article);
            }
            return articles;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
