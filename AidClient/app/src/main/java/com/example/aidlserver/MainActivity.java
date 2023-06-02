package com.example.aidlserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aidclient.R;

public class MainActivity extends AppCompatActivity {
    IaidlInterface iaidlInterface;


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iaidlInterface = IaidlInterface.Stub.asInterface(iBinder);
            System.out.println("ok1");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent("CalculatorService");
        intent.setPackage("com.example.calculator");

        bindService(intent,mConnection,BIND_AUTO_CREATE);
        System.out.println("ok2");

        Button button = findViewById(R.id.btn1);
        EditText num1 = findViewById(R.id.num1);
        EditText num2 = findViewById(R.id.num2);
        EditText res = findViewById(R.id.res);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value1 = Integer.parseInt(num1.getText().toString());
                int value2 = Integer.parseInt(num2.getText().toString());
                try {
                    int result = iaidlInterface.add(value1, value2);
                    res.setText(result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            });
    }
}
