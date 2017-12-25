package com.mark.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.mark.aidl.IClientCallback;
import com.mark.aidl.IMyAidlInterface;
import com.mark.aidl.entry.CodeEntry;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by Mark on 2017/12/22.
 */

public class RemoteService extends Service {
    private boolean isDestroy = true;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        isDestroy = false;
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroy = true;
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

        @Override
        public void randomNum(final IClientCallback callback) throws RemoteException {
         final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!isDestroy) {
                        try {
                            if (callback != null)
                                callback.callback(new CodeEntry("Mark", getRandomNum()));
                               Thread.sleep(2000);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
         thread.start();

        }
    };

}
