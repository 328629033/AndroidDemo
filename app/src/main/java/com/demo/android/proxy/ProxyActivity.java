package com.demo.android.proxy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.android.mulitprocess.Printer;

/**
 * Created by herr.wang on 2018/1/3.
 */

public class ProxyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProxyTest.instance.setSubject(subject);
        ProxyTest.instance.startWork();
    }

    Subject subject = new Subject() {
        @Override
        public void hello() {
            Printer.print("hello proxy." + Thread.currentThread().getName());
        }
    };

}
