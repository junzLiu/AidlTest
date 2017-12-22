package com.mark.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mark.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    TextView codeTv, errTv;
    Button linkBtn, getCodeBtn;

    ServiceConnection mConnection;
    IMyAidlInterface myAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codeTv = findView(R.id.tv_code);
        errTv = findView(R.id.err_text);
        linkBtn = findView(R.id.btn_link);
        getCodeBtn = findView(R.id.btn_getCode);
        getCodeBtn.setEnabled(false);
        initConnection();

        linkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.mark.aidl.action.AIDL_SERVICE");
                i.setPackage("com.mark.aidl");
                bindService(i, mConnection, BIND_AUTO_CREATE);
            }
        });

        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (myAidlInterface != null) {
                        codeTv.setText(myAidlInterface.getRandomNum() + "");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initConnection() {
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                linkBtn.setText(R.string.linked);
                linkBtn.setEnabled(false);
                getCodeBtn.setEnabled(true);
                errTv.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myAidlInterface = null;
                errTv.setText(R.string.err_link_fail);
                errTv.setVisibility(View.VISIBLE);
                linkBtn.setText(R.string.to_link);
                linkBtn.setEnabled(true);
                getCodeBtn.setEnabled(false);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    private <T extends View> T findView(int id) {
        return (T) (findViewById(id));
    }
}
