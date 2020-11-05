package com.example.loginactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.BitSet;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class ImgFragment extends Fragment {
        ImageView imageView ;
        Button button ;

    public ImgFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    private void sendRequestOkHttpRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("https://gank.io/api/v2/data/category/Article/type/Android/page/1/count/10").build();
                    Response response = client.newCall(request).execute();
                    String responseDate = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseDate);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for(int i =0 ;i<data.length();i++){
//                        JSONObject jsonitem = data.getJSONObject(i);
                        JSONObject img = data.getJSONObject(i);

                        JSONArray imgurl  = img.getJSONArray("images");

                        OkHttpClient client1 = new OkHttpClient();
                        Request request1 = new Request.Builder().url(imgurl.get(0).toString()).build();
                        ResponseBody body = client1.newCall(request1).execute().body();
                        InputStream in = body.byteStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(in);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });


                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();


    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView = getView().findViewById(R.id.imageView);
        button = getView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestOkHttpRequest();
                Intent intentService = new Intent(getActivity(), MyService.class);
                getActivity().startService(intentService);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_img, container, false);
    }
}