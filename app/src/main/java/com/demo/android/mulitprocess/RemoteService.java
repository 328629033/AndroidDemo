package com.demo.android.mulitprocess;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by herr.wang on 2017/8/28.
 */

public class RemoteService extends Service {
    Messenger serverMessenger, clientMessenger;

    class ServerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            clientMessenger = msg.replyTo;
            Printer.print(data.getString("client"));
            if(clientMessenger != null){

                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("server", "I am server");
                message.setData(bundle);
                try {
                    clientMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serverMessenger = new Messenger(new ServerHandler());

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serverMessenger.getBinder();
    }


}
