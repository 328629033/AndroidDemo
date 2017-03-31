package com.demo.android.animation.handler;

import android.animation.ObjectAnimator;
import android.widget.FrameLayout;

/**
 * Created by herr.wang on 2017/3/14.
 * maybe declare as abstract will be better.
 */

public class EventHandler {
    public void onClick(FrameLayout targetLayout){
        //TODO animation
        ObjectAnimator upAnimator = ObjectAnimator.ofFloat(targetLayout, "translationY", targetLayout.getTranslationY(), 0);
        upAnimator.setDuration(300);
        upAnimator.start();
    }
}
