package com.example.pan.myapplication.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.pan.myapplication.R;

/**
 * Created by pan on 2018/4/7.
 */

public class MessengerActivity extends AppCompatActivity {

    private boolean mBound=false;
    private Messenger messenger;
    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            messenger=new Messenger(service);
            mBound = true;
        }


        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Messenger Test");
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    private void init(){
        // Bind to LocalService
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void action(View view){
        if (null!=messenger && mBound){
            Message message=Message.obtain();
            message.what=MessengerService.MSG_RANDOM;
            try{
                messenger.send(message);
            }catch (RemoteException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }
}
