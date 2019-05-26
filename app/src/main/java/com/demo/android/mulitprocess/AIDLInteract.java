package com.demo.android.mulitprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

/**
 * Created by herr.wang on 2017/8/28.
 */

public class AIDLInteract implements IInteract {
    private Context context;
    private boolean mBound;

    IHailManager hailManager;

    IRemoteServiceCallback.Stub iRemoteServiceCallback = new IRemoteServiceCallback.Stub(){

        @Override
        public void onResponse(String response) throws RemoteException {
            Printer.print(response);
        }
    };

    IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Printer.print("binder died.");
        }
    };

    public AIDLInteract(Context context) {
        this.context = context;
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            try {
                service.linkToDeath(deathRecipient, 0);
                hailManager = IHailManager.Stub.asInterface(service);
                hailManager.register(iRemoteServiceCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    public void connect2Server() {
        if (!mBound) {
            Intent it = new Intent();
            it.setAction("com.leeks.aidl");
            it.setPackage("com.demo.android");
            context.bindService(it, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    public void sendMessage() {
        if (mBound) {
            if (hailManager != null) {
                Hail hail = new Hail("client", "are you ok?", Process.myPid());
                try {
                    hailManager.doHail(hail);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void disConnect() {
        if(mBound) {
            context.unbindService(serviceConnection);
        }
    }
}
