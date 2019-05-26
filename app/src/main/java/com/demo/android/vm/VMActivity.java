package com.demo.android.vm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by herr.wang on 2017/8/16.
 */

public class VMActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String hello = new String("hello");
        String world = new String("world");
        String nice = new String("hello");
        System.out.println(hello + world + nice);
    }
}
