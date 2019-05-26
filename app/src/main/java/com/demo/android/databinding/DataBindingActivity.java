package com.demo.android.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.demo.android.R;
import com.demo.android.mulitprocess.Printer;

import java.lang.ref.WeakReference;

/**
 * Created by herr.wang on 2017/3/9.
 */

public class DataBindingActivity extends AppCompatActivity {
    Activity activity;
    MyHandler myHandler;
    ImageView ivTest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        
        final com.demo.android.databinding.ActivityDatabindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        final User user = new User();
        ivTest = binding.ivTest;
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
//                user.setName("zhangsan");
//                binding.setUser(user);
                Printer.print("--------------");
//                ivTest.clearAnimation();
                ivTest.setVisibility(View.GONE);
            }
        }, 5000);
        Task task = new Task();
        binding.setTask(task);
//        Picasso.with(this).load("http://192.168.1.192/pics//g/20170223202922815_0.jpg").error(R.drawable.flower).into(binding.ivTest);
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


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("onResume...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
