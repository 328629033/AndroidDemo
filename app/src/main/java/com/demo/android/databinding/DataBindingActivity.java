package com.demo.android.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.android.R;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

/**
 * Created by herr.wang on 2017/3/9.
 */

public class DataBindingActivity extends AppCompatActivity {
    Activity activity;
    MyHandler myHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        final ActivityDatabindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        final User user = new User();
        Fruit fruit = new Fruit("orange", "yellow");
        user.fruit = fruit;
        user.setName("lisi");
        user.setAge(18);
        binding.setUser(user);
        EventHandler eventHandler = new EventHandler();
        binding.setHandler(eventHandler);
        myHandler = new MyHandler(this);
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setName("zhangsan");
                binding.setUser(user);
            }
        }, 10000);
        Task task = new Task();
        binding.setTask(task);
        Picasso.with(this).load("http://192.168.1.192/pics//g/20170223202922815_0.jpg").error(R.drawable.flower).into(binding.ivTest);
    }

    static class MyHandler extends Handler{
        private final WeakReference<Activity> mActivity;
        public MyHandler(Activity activity){
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }



}
