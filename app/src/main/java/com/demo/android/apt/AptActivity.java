package com.demo.android.apt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.demo.android.R;
import com.leeks.apt.annotation.Bind;
import com.leeks.apt.annotation.OnMsg;

/**
 * Created by herr.wang on 2017/3/24.
 */


public class AptActivity extends AppCompatActivity {
    @Bind(id=R.id.tv_hello, name = "hello world.")
    TextView tvHello;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apt);
    }

    @OnMsg(cmds = {1, 2}, isRunOnUI = false)
    public void initView(){

    }
}
