package com.demo.android.selfview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.demo.android.R;
import com.demo.android.databinding.ActivityViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/3/13.
 */

public class ViewActivity extends AppCompatActivity {
    private static final String TAG = "ViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view);
        List<String> list = new ArrayList<>();
        list.add("第一个");
        list.add("第二个");
        list.add("第三个");
        list.add("第四个");
        binding.setTabList(list);
        binding.setCallback(new SlideSwitchButton.OnSlideCallback() {
            @Override
            public void onTabChanged(int index) {
                Log.i(TAG, "tab " + index + " checked.");
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               binding.gbvTest.startGradientAnimation();
            }
        }, 5000);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.gbvTest.stopGradientAnimation();
            }
        }, 200000);

    }

    static Handler handler = new Handler();


}
