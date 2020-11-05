package com.example.loginactivity;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;


public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }


    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);

        context.startService(intent);
    }



    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);

        context.startService(intent);
    }

    @Override
    //子线程里处理的逻辑
    protected void onHandleIntent(Intent intent) {
        Log.d("intentserver", "onHandleIntent: 这是intentserver");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("server", "onDestroy: 线程结束了");
    }

    private void handleActionFoo(String param1, String param2) {

        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
