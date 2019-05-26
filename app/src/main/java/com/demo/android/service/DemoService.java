package com.demo.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.demo.android.mulitprocess.Printer;

/**
 * Created by herr.wang on 2017/9/6.
 */

public class DemoService extends Service {
    DemoBinder demoBinder = new DemoBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        Printer.print("DemoService onCreate...");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Printer.print("DemoService onStart...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Printer.print("DemoService onBind...");
        return demoBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Printer.print("DemoService onUnbind...");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Printer.print("DemoService onDestroy...");
        super.onDestroy();
    }

    public class DemoBinder extends Binder {

        public DemoService getService(){
            return DemoService.this;
        }
    }
}
