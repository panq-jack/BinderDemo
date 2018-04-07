package com.example.pan.myapplication.aidl;

import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.pan.myapplication.IMsgManager;

/**
 * Created by pan on 2018/4/7.
 */

public class MsgManagerImpl extends IMsgManager.Stub {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        Log.d("ppp","basicType");
    }

    @Override
    public void sendMsg() throws RemoteException {
        Log.d("ppp","sendMsg: ");
    }

    @Override
    public String getMsg() throws RemoteException {
        return "msg from  MsgManagerImpl";
    }

    @Override
    public Msg getParcelableMsg() throws RemoteException {
        Msg msg=new Msg();
        msg.setAge(30);
        msg.setName("qpan-jack");
        return msg;
    }
}
