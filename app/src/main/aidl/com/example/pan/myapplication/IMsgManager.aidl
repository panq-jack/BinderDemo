// IMsgManager.aidl
package com.example.pan.myapplication;

// Declare any non-default types here with import statements
import com.example.pan.myapplication.aidl.Msg;

interface IMsgManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void sendMsg();

    String getMsg();

    Msg getParcelableMsg();

}
