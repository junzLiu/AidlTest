package com.mark.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.mark.aidl.IMyAidlInterface;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by Mark on 2017/12/22.
 */

public class RemoteService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public int getRandomNum() throws RemoteException {
            return new Random(Calendar.getInstance().getTimeInMillis()).nextInt();
        }
    };
}
