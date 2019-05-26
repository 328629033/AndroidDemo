// IHailManager.aidl
package com.demo.android.mulitprocess;
import com.demo.android.mulitprocess.Hail;
import com.demo.android.mulitprocess.IRemoteServiceCallback;

// Declare any non-default types here with import statements

interface IHailManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void doHail(in Hail hail);

    void register(in IRemoteServiceCallback callback);

    void unRegister(in IRemoteServiceCallback callback);
}
