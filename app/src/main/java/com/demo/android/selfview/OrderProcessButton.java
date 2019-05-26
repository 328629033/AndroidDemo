package com.demo.android.selfview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.demo.android.R;
import com.demo.android.order.OrderProcessBinding;


/**
 * Created by herr.wang on 2017/6/30.
 */

public class OrderProcessButton extends FrameLayout {

    private int mTouchSlop;
    private int mWidth;

    TextView tvSlider;
    TextView tvBackground;

    private SlideCallback callback;

    public OrderProcessButton(Context context) {
        this(context, null);
    }

    public OrderProcessButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OrderProcessButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        OrderProcessBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_order_process, this, true);
        tvSlider = binding.tvSlider;
        tvBackground = binding.tvBg;
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    private float x, xOffset;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                xOffset = event.getX() - x;
                x = event.getX();
                System.out.println("move..." + xOffset);
                doSlide(xOffset);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                slide();
                break;
        }
        return true;
    }

    private void doSlide(float x) {

        if (tvSlider.getTranslationX() < mWidth) {
            tvSlider.setTranslationX(tvSlider.getTranslationX() + x);
        }
    }

    ValueAnimator translateAnim;

    private void slide() {
        if (Math.abs(tvSlider.getTranslationX()) > mWidth / 3) {
            translateAnim = ValueAnimator.ofFloat(tvSlider.getTranslationX(), mWidth);
            if (callback != null) {
                callback.onNext();
            }
        } else {
            translateAnim = ValueAnimator.ofFloat(tvSlider.getTranslationX(), 0);
        }
        translateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tvSlider.setTranslationX((Float) animation.getAnimatedValue());
            }
        });
        translateAnim.start();
    }


    public void setSlideText(String text){
        tvSlider.setVisibility(View.VISIBLE);
        tvSlider.setText(text);
        tvSlider.setTranslationX(0);
//        tvBackground.setBackgroundResource(R.drawable.bg_ff7a3f_corner_2);
//        tvBackground.setText(null);
    }

//    public void showStatus(){
//        tvSlider.setVisibility(View.GONE);
//        tvBackground.setBackgroundResource(R.drawable.bg_cccccc_corner_2);
//        tvBackground.setText(getResources().getString(R.string.arrived));
//    }

    public void setSlideCallback(SlideCallback callback) {
        this.callback = callback;
    }

    public interface SlideCallback {
        void onNext();
    }

    public void destroy() {
        if (translateAnim != null && translateAnim.isRunning()) {
            translateAnim.cancel();
        }
    }
}
