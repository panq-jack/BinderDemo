package com.example.pan.myapplication.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by pan on 2018/4/7.
 */

public class MessengerService extends Service {
    public final static int MSG_RANDOM=0;

    // Random number generator
    private final Random mGenerator = new Random();

    private Handler messengerHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_RANDOM:
                    int randomNum=mGenerator.nextInt(100);
                    Toast.makeText(getApplicationContext(),"randomNum  :"+randomNum,Toast.LENGTH_SHORT).show();

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
