package com.wutong.pictureinformation;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.wutong.aidlserver.IMyAidlInterface;
import com.wutong.pictureinformation.adapter.MyFragmentAdapter;
import com.wutong.pictureinformation.fragment.ArticleFragment;
import com.wutong.pictureinformation.fragment.PersonalFragment;
import com.wutong.pictureinformation.fragment.PictureFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private ArrayList fragments;
    private IMyAidlInterface aidl;

    private final ServiceConnection conn = new ServiceConnection() {
        //绑定成功时调用
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            aidl=IMyAidlInterface.Stub.asInterface(iBinder);//获取IMyAidlInterface对象 将服务端的Binder对象转换为客户端所需要的接口对象
            Log.d("Client","绑定成功了" );
            try {
                Log.d("客户端返回结果", "sum is "+aidl.add(1,2));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        //绑定断开时回调
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("Client","绑定服务失败了" );
            aidl=null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
        init();

        //获取服务端连接， 绑定服务
        Intent intent=new Intent()
                .setAction("com.wutong.aidlserver.myaidlservice")
                .setPackage("com.wutong.aidlserver");
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
        Log.d("Client"," bindService ...");
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑服务
        unbindService(conn);
    }

    private void init() {
        myViewPager=findViewById(R.id.myViewPager);
        tabLayout=findViewById(R.id.tabLayout);
        ArticleFragment articleFragment=new ArticleFragment();
        PictureFragment pictureFragment=new PictureFragment();
        PersonalFragment personalFragment=new PersonalFragment();
        fragments=new ArrayList<Fragment>();
        fragments.add(articleFragment);//将fragment添加到list集合中
        fragments.add(pictureFragment);
        fragments.add(personalFragment);
        FragmentManager fragmentManager=getSupportFragmentManager();//获取FragmentManager
        MyFragmentAdapter myFragmentAdapter=new MyFragmentAdapter(fragmentManager,fragments);
        myViewPager.setAdapter(myFragmentAdapter);
        tabLayout.setupWithViewPager(myViewPager);//把TabLayout和ViewPager组合,使用setupWithViewPager可以让TabLayout和ViewPager联动
    }




    //android6.0之后要动态获取权限
    private void requestPermission() {
        // Storage Permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:

                if (grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                }else {
                    Toast.makeText(this,"您拒绝了读写权限",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}