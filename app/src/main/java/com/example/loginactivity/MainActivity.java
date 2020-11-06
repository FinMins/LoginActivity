package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        private ServiceConnection connection = new ServiceConnection() {
            @Override//绑定成功时调用
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override//解绑时调用
            public void onServiceDisconnected(ComponentName name) {

            }
        }
    }
}