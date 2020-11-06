package com.wutong.pictureinformation.service;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import com.wutong.pictureinformation.util.HttpUtil;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DownloadService extends Service {

    private static final String TAG="DownloadService";
    public DownloadService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "创建Service ");
    }

    /**
     * 服务启动时调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "启动Service ");
        String url=intent.getStringExtra("url");
        /*Log.d("Intent传过来的图片地址", url);*/
        new DownloadTask().execute(url);//启动下载任务
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "销毁Service ");
        super.onDestroy();
    }

    class DownloadTask extends AsyncTask<String,Void,String>{
        /**
         * 异步线程下载图片，在子线程中运行 处理耗时任务
         * @param strings
         * @return
         */
        @Override
        protected String doInBackground(String... strings) {
            getPicStream(strings[0]);
            return null;
        }

        //
        private void getPicStream(final String downloadUrl) {
            HttpUtil.sendOkHttpRequest(downloadUrl, new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    InputStream inputStream=response.body().byteStream();
                    Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                    //文件名
                    String dirPath=Environment.getDataDirectory()+"/data/com.wutong.pictureinformation/files";
                    File file=new File(dirPath);
                    if (!file.exists()){
                        file.mkdir();
                    }

                    String fileName=downloadUrl.substring(downloadUrl.lastIndexOf("/"))+".jpg";
                    FileOutputStream fileOutputStream=new FileOutputStream(dirPath+fileName);
                    Log.d("文件名",dirPath+fileName);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();

                    MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,fileName,null);
                    Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file));
                    intent.putExtra("dirPath",dirPath);
                    intent.putExtra("ok",1);
                    sendBroadcast(intent);//发送广播 通知系统图库更新
                }
            });
        }
    }
}
