package com.wutong.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyAidlService extends Service {
    public MyAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return iBinder;
    }




    private IBinder iBinder=new IMyAidlInterface.Stub() {//实现接口 并向客户端开放接口

        @Override
        public int add(int value1, int value2) throws RemoteException {
            return value1+value2;
        }
    };
}
