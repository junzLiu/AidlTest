// IMyAidlInterface.aidl
package com.mark.aidl;

// Declare any non-default types here with import statements
import com.mark.aidl.IClientCallback;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

     /*
      *  get Process id
      */
     int getPid();

     int getRandomNum();

     void randomNum(IClientCallback callback);
}
