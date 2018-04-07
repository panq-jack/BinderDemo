package com.example.pan.myapplication.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by pan on 2018/4/7.
 */

public class MessengerService extends Service {
    public final static int MSG_FROM_CLIENT =0;

    // Random number generator
    private final Random mGenerator = new Random();

    private Handler messengerHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_FROM_CLIENT:
                    Bundle bundle=msg.getData();
                    String str=bundle.getString("msg","");
                    Toast.makeText(getApplicationContext(),"msg from client  :"+str,Toast.LENGTH_SHORT).show();


                    Bundle toBundle=new Bundle();
                    toBundle.putString("msg","msg from server by messenger author jackpanq");
                    Message message=Message.obtain(null,MessengerActivity.MSG_FROM_SERVER);
                    message.setData(toBundle);
                    Messenger messenger=msg.replyTo;
                    try {
                        messenger.send(message);
                    }catch (RemoteException e){
                        e.printStackTrace();
                    }

                    break;
                    default:
                        break;
            }
        }
    };

    private Messenger messenger=new Messenger(messengerHandler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
