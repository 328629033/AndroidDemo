package com.demo.android.mulitprocess;

/**
 * Created by herr.wang on 2017/9/7.
 */

public class NativeInteract implements IInteract {
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void connect2Server() {
        int pid = createProcess();
        Printer.print(pid + "");
    }

    @Override
    public void sendMessage() {
        Printer.print(send2Native());
    }

    @Override
    public void disConnect() {
        killProcess();
    }

    public native String getMessage();

    public native int createProcess();

    public native String send2Native();

    public native void killProcess();

}
