package com.example.pan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.pan.myapplication.aidl.AIDLActivity;
import com.example.pan.myapplication.binder.BinderActivity;
import com.example.pan.myapplication.messenger.MessengerActivity;

/**
 * Created by pan on 2018/4/7.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void binderTest(View view){
        Intent intent=new Intent(this, BinderActivity.class);
        startActivity(intent);
    }

    public void MessengerTest(View view){
        Intent intent=new Intent(this, MessengerActivity.class);
        startActivity(intent);
    }

    public void AIDLTest(View view){
        Intent intent=new Intent(this, AIDLActivity.class);
        startActivity(intent);
    }
}
