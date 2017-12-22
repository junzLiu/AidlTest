package com.mark.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mark.aidl.service.RemoteService;

public class MainActivity extends AppCompatActivity {

    ServiceConnection mConnection;
    IMyAidlInterface mAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initConnection();
        bindService(new Intent(this, RemoteService.class),mConnection,BIND_AUTO_CREATE);
    }

    private void initConnection(){
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
               mAidlInterface = null;
            }
        };
    }
}
