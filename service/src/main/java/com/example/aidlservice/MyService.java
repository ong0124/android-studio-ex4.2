package com.example.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new mbinder();
    }

    public class  mbinder extends Icalculator.Stub{

        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }
    }
}