package com.wutong.pictureinformation.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {

    /**
     * Http工具类
     * @param url   解析的地址
     * @param callback  自带的回调接口
     *   OKHttpUtil.sendOkHttpRequest("http://baidu.com",new Callback(){
     *
     *   }
     */

    public static void sendOkHttpRequest(String url, Callback callback){//callback回调接口
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);//在enqueue内部已经开好子线程，并在子线程中执行http请求 结果回调在callback中
        // 回调接口在子线程中运行 不能执行ui操作
    }


}
