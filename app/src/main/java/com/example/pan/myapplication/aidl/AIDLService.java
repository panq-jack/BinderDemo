package com.example.pan.myapplication.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by pan on 2018/4/7.
 */

public class AIDLService extends Service {



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MsgManagerImpl();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
