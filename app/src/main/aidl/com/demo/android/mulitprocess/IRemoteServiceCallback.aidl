// IRemoteServiceCallback.aidl
package com.demo.android.mulitprocess;

// Declare any non-default types here with import statements

oneway interface IRemoteServiceCallback {

    void onResponse(String response);
}
