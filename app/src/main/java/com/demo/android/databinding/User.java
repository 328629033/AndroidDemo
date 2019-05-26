package com.demo.android.databinding;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.demo.android.BR;
import com.demo.android.R;
import com.demo.android.mulitprocess.Printer;

/**
 * Created by herr.wang on 2017/3/9.
 */

public class User extends BaseObservable {
    private String name;
    private int age;
    public String url = "http://192.168.1.192/pics//g/20170223202922815_0.jpg";

    public Fruit fruit;

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    public int getResource(){
        return 0;
    }

    public Animation getAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.start();
        return rotateAnimation;
    }

    @BindingAdapter({"imageResource", "animation"})
    public static void anim(final ImageView view, int imageResource, Animation animation) {
        view.setImageResource(R.drawable.head);
        Printer.print("anim...........");
        final ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360.0f);
        rotateAnim.setRepeatCount(-1);
        rotateAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if(view.getVisibility() == View.GONE){
                    rotateAnim.cancel();
                }
                Printer.print("anim repeat....");
            }
        });
        rotateAnim.start();
    }
}
