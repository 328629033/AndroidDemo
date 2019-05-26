package com.demo.android.mulitprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by herr.wang on 2017/8/28.
 */

public class RemoteService2 extends Service {

    final RemoteCallbackList<IRemoteServiceCallback> callbackList = new RemoteCallbackList<>();


    IHailManager.Stub hailManager = new IHailManager.Stub() {
        IRemoteServiceCallback callback;
        @Override
        public void doHail(Hail hail) throws RemoteException {
            Printer.print(hail.toString());
            int N = callbackList.beginBroadcast();
            for(int i=0; i<N; ++i){
                callbackList.getBroadcastItem(i).onResponse("hi, I am server");
            }
            callbackList.finishBroadcast();
            callback.onResponse("hi, I'm fine.");
        }

        public void register(IRemoteServiceCallback callback){
            callbackList.register(callback);
            this.callback = callback;
        }


        public void unRegister(IRemoteServiceCallback callback){
            callbackList.unregister(callback);
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return hailManager;
    }
}
