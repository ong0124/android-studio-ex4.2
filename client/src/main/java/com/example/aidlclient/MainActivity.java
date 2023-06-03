package com.example.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aidlservice.Icalculator;

public class MainActivity extends AppCompatActivity {

    private Icalculator myAidlInterface = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                myAidlInterface = Icalculator.Stub.asInterface(iBinder);
                try {
                    int result = myAidlInterface.add(2, 3);
                        Toast.makeText(MainActivity.this, "Result: " + result, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        Intent intent = new Intent();
        intent.setAction("com.example.service.action");
        intent.setPackage("com.example.aidlservice");
        bindService(intent,connection,BIND_AUTO_CREATE);
    }
}





