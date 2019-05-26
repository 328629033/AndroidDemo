package com.demo.android.mulitprocess;

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

/**
 * Created by herr.wang on 2017/8/28.
 */

public class MessengerInteract implements IInteract {
    private Context context;
    private Messenger clientMessenger, serverMessenger;

    class ClientHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            if(bundle != null){
                Printer.print(bundle.getString("server"));
            }
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Printer.print("onServiceConnected...");
            serverMessenger = new Messenger(service);
            clientMessenger = new Messenger(new ClientHandler());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public MessengerInteract(Context context){
        this.context = context;

    }

    public void connect2Server(){
        Intent it = new Intent(context, RemoteService.class);
        context.bindService(it,serviceConnection, Context.BIND_AUTO_CREATE );
    }

    @Override
    public void sendMessage() {
        Message message = Message.obtain();
        message.replyTo = clientMessenger;
        Bundle bundle = new Bundle();
        bundle.putString("client", "I am client.");
        message.setData(bundle);
        try {
            serverMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void disConnect() {
        context.unbindService(serviceConnection);
    }


}
