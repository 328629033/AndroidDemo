package com.demo.android;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.demo.android.mulitprocess.Printer;

/**
 * Created by herr.wang on 2017/11/6.
 */

public class NotificationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Printer.print("onCreate...");
        Notification.Builder builder = new Notification.Builder(this);
        Intent it = new Intent(this, MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, it, 0)).
                setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)).
                setContentTitle("这是前台服务").setContentText("这是服务内容").
                setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.ic_launcher);
        Notification notification = builder.getNotification();
        startForeground(100, notification);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
//               Printer.print("start from background...");
               Intent it = new Intent(getApplicationContext(), MainActivity.class);
               it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//               it.setAction(Intent.ACTION_MAIN);
//               it.addCategory(Intent.CATEGORY_LAUNCHER);
//               getApplicationContext().startActivity(it);
           }
       }, 5000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Printer.print("onStartCommand...");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Printer.print("notification service onDestroy...");
        super.onDestroy();
    }
}
