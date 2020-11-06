package com.example.loginactivity;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.lang.reflect.Parameter;

import javax.xml.transform.Result;

public class MyAsyncTaksk extends Service {
        private Binder binder = new Binder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return this.binder;
    }
}
