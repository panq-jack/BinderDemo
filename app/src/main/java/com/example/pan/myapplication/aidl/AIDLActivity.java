package com.example.pan.myapplication.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.pan.myapplication.IMsgManager;
import com.example.pan.myapplication.R;

/**
 * Created by pan on 2018/4/7.
 */

public class AIDLActivity extends AppCompatActivity {

    private boolean mBound;
    IMsgManager iMsgManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("AIDL Test");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,AIDLService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            iMsgManager=IMsgManager.Stub.asInterface(service);
            mBound = true;
        }


        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    public void send(View view){
        try{
            iMsgManager.sendMsg();
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

    public void getString(View view){
        try{
            String msg=iMsgManager.getMsg();
            Toast.makeText(this,"msg: "+msg,Toast.LENGTH_SHORT).show();
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

    public void getParcelableObject(View view){
        try{
            Msg msg=iMsgManager.getParcelableMsg();
            Toast.makeText(this,"msgObj: "+msg.toString(),Toast.LENGTH_SHORT).show();
        }catch (RemoteException e){
            e.printStackTrace();
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
