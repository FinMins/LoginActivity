package com.example.loginactivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    NotificationChannel notificationChannel = null;
    String CHANNEL_ONE_ID = "CHANNEL_ONE_ID";
    String CHANNEL_ONE_NAME= "CHANNEL_ONE_ID";
    public MyService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
//        Notification notification = new NotificationCompat.Builder(this).setContentTitle("biaoti")
//                .setContentText("内容")
//                .setWhen(System.currentTimeMillis())
//                .setContentIntent(pendingIntent)
//                .setChannelId("com.primedu.cn")
//                .build();
//        startForeground(1,notification);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel= new NotificationChannel(CHANNEL_ONE_ID,
                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
        }
      Intent intent1 = new Intent(this,MainActivity.class);
      PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
Notification notification = new NotificationCompat.Builder(this).setChannelId("CHANNEL_ONE_ID")
        .setContentTitle("这是标题")
        .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
        .setContentText("要显示的内容") // 设置上下文内容
        .setWhen(System.currentTimeMillis())
        .setContentIntent(pendingIntent)
        .build();
        notification.flags|= Notification.FLAG_NO_CLEAR;
startForeground(1,notification);
        return super.onStartCommand(intent, flags, startId);
  }
}
