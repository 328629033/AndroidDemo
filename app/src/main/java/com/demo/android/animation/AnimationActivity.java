package com.demo.android.animation;

import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.demo.android.R;
import com.demo.android.animation.handler.EventHandler;
import com.demo.android.databinding.ActivityAnimationBinding;

/**
 * Created by herr.wang on 2017/3/14.
 */

public class AnimationActivity extends AppCompatActivity{
    ListFragment listFragment;
    ConfigFragment configFragment;
    TextView tvTitle;
    ActivityAnimationBinding binding;
    FrameLayout flConfig;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animation);
        flConfig = binding.flConfig;
        listFragment = new ListFragment();
        configFragment = new ConfigFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl_list, listFragment);
        ft.add(R.id.fl_config, configFragment);
        ft.commit();
        EventHandler eventHandler = new EventHandler();
        binding.setHandler(eventHandler);
        tvTitle = binding.tvTitle;
        binding.flConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("onClick...");
            }
        });

    }
    float offset;

    private boolean needGetData = true;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(needGetData){
            int top = binding.tvTitle.getBottom();
            int bottom = binding.rlBottom.getTop();
            offset = bottom - top - dp2Px(20);
            needGetData = false;
            ObjectAnimator upAnimator = ObjectAnimator.ofFloat(binding.flConfig, "translationY", 0.0f, -offset);
            upAnimator.setDuration(0);
            upAnimator.start();
            binding.flConfig.setVisibility(View.VISIBLE);
        }

    }

    private float dp2Px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onBackPressed() {
        if(flConfig.getTranslationY() == 0) {
            ObjectAnimator upAnimator = ObjectAnimator.ofFloat(flConfig, "translationY", 0, -offset);
            upAnimator.setDuration(300);
            upAnimator.start();
        }else{
            super.onBackPressed();
        }
    }
}
