package com.demo.android.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.android.MainService;
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
    TextView tvScale;
    LinearLayout llScale, llChild;
    Button btnScale;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animation);
        flConfig = binding.flConfig;
        tvScale = binding.tvScale;
        llScale = binding.llScale;
        llChild = binding.llChild;
        btnScale = binding.btnScale;
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

        Intent it = new Intent(this, MainService.class);
        bindService(it, serviceConnection, BIND_AUTO_CREATE);

    }

    ServiceConnection serviceConnection = new  ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.print("disconnect...");
        }
    };

    float offset;

    private boolean needGetData = true;
    int parentHeight, childHeight;
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
        parentHeight = llScale.getHeight();

        childHeight = llChild.getHeight();
        System.out.println("-----" + childHeight);
        doScaleAnim();
    }

    void doScaleAnim(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(childHeight, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams lp = llScale.getLayoutParams();
                ViewGroup.LayoutParams childLp = llChild.getLayoutParams();
                childLp.height = value;
                lp.height = parentHeight - childHeight + value;
                llChild.setLayoutParams(childLp);
                llScale.setLayoutParams(lp);
                System.out.println(value + "\t" + lp.height + "\t" + llScale.getHeight());
//                llScale.setLayoutParams(lp);
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
