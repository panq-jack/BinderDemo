package com.example.pan.myapplication.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pan.myapplication.R;

/**
 * Created by pan on 2018/4/7.
 */

public class MessengerActivity extends AppCompatActivity {

    public static final int MSG_FROM_SERVER=1;
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

    private Handler clientHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_FROM_SERVER:
                    Bundle bundle=msg.getData();
                    String str=bundle.getString("msg","");
                    Toast.makeText(MessengerActivity.this,"msg from server: "+str,Toast.LENGTH_SHORT).show();
                    break;
                    default:
                        break;
            }
        }
    };

    private  Messenger clientMessenger=new Messenger(clientHandler);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Messenger Test");
        actionBar.setDisplayHomeAsUpEnabled(true);
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
            //发送消息，并设置接收消息处理器
            Message message=Message.obtain();
            message.what=MessengerService.MSG_FROM_CLIENT;
            Bundle bundle=new Bundle();
            bundle.putString("msg","msg from client by Messenger   author jackpanq");
            message.setData(bundle);
            message.replyTo=clientMessenger;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
