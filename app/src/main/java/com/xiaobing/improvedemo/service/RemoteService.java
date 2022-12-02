package com.xiaobing.improvedemo.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.joker.common.ITest;


public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Binder", "onBind--" + intent);
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Binder", "onCreate--");
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("Binder", "onStart--");
    }

    @Override
    public ComponentName startService(Intent service) {
        Log.e("Binder", "startService--");
        return super.startService(service);
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        Log.e("Binder", "startForegroundService--");
        return super.startForegroundService(service);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Binder", "onStartCommand--");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Binder", "onUnbind--");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("Binder", "onDestroy--");
        super.onDestroy();
        System.exit(0);
    }

    private final IBinder iBinder = new ITest.Stub() {
        @Override
        public int add(int a, int b) {
            Log.e("Binder", "ITest.Stub add-- a="+ a+"  b="+ b);
            return a + b;
        }

        @Override
        public void send(int a, int b) {
            Log.e("Binder", "ITest.Stub send-- a="+ a+"  b="+ b);
        }
    };
}
