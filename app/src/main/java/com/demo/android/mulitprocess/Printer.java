package com.demo.android.mulitprocess;

import android.util.Log;

/**
 * Created by herr.wang on 2017/8/28.
 */

public class Printer {
    public static void print(String printing) {
        System.out.println(printing);
    }

    public static void log(String log) {
        Log.e("", log);
    }
}
