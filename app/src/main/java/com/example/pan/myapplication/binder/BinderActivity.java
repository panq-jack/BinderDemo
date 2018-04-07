package com.example.pan.myapplication.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pan.myapplication.R;

public class BinderActivity extends AppCompatActivity {

    BinderService mService;
    boolean mBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Binder Test");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    private void init(){
        // Bind to LocalService
        Intent intent = new Intent(this, BinderService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void action(View view){
        if (null!=mService && mBound){
            int randomNum=mService.getRandomNumber();
            Toast.makeText(this,"randomNum: "+randomNum,Toast.LENGTH_SHORT).show();
        }

    }


    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BinderService.LocalBinder binder = (BinderService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }


        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

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
