package com.demo.android;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by herr.wang on 2017/6/6.
 */

public class MainService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("MainService onCreate...");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("MainService onBind...");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("MainService onStart...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("MainService onUnbind...");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        System.out.println("MainService onDestroy...");
        super.onDestroy();
    }

}
