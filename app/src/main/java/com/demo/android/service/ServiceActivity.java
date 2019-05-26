package com.demo.android.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.android.mulitprocess.Printer;

/**
 * Created by herr.wang on 2017/9/6.
 *
 * single or multi process:
 *      1.bind when create but do not unbind when destroy
 *      2.start but do not stop
 *
 * result:
 *     1.start service N(>1) times will only call onCreate once, call onStart N times.
 *     2.bind without unbind will leak the service connection, then service will unbind and destroy automatically.
 *     3.next bind will not trigger onCreate or onBind, actually, no life-cycle method is called.
 */

public class ServiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent it = new Intent(this, DemoService.class);
        it.addFlags(Service.START_STICKY);
        bindService(it, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Printer.print("ServiceActivity onDestroy...");
    }
}
