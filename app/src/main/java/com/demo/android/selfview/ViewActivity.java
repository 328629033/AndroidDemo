package com.demo.android.selfview;

import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.demo.android.R;
import com.demo.android.databinding.ActivityViewBinding;
import com.demo.android.util.WindowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/3/13.
 */

public class ViewActivity extends AppCompatActivity {
    private static final String TAG = "ViewActivity";
    ActivityViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view);
//        View rootView = ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
//        rootView.setVisibility(View.GONE);
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
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//               binding.gbvTest.startGradientAnimation();
//            }
//        }, 5000);
////
////
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                binding.gbvTest.stopGradientAnimation();
//            }
//        }, 200000);

        final OrderProcessButton orderProcessButton = binding.opb;
        orderProcessButton.setSlideCallback(new OrderProcessButton.SlideCallback() {
            @Override
            public void onNext() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        orderProcessButton.setSlideText("hello");
                    }
                });
            }
        });
        binding.tvSpan.setText(getSpan());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                change2Small();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        recoverySize();
//                    }
//                }, 2000);
//            }
//        }, 3000);

        binding.ripple.start();
    }

    public void change2Small(){
        Window window = getWindow();
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams attrs = window.getAttributes();
        attrs.width = 1;
        attrs.height = 1;
        attrs.x = 0;
        attrs.y = 0;
        window.setBackgroundDrawableResource(R.color.color_00000000);
        window.setAttributes(attrs);
    }

    public void recoverySize(){
        Window window = getWindow();
        WindowManager.LayoutParams attrs = window.getAttributes();
        attrs.width = ViewGroup.LayoutParams.MATCH_PARENT;
        attrs.height = ViewGroup.LayoutParams.MATCH_PARENT;
        attrs.x = 0;
        attrs.y = 0;
        window.setAttributes(attrs);
    }

    static Handler handler = new Handler();

    private SpannableStringBuilder getSpan(){
        SpannableStringBuilder ssb = new SpannableStringBuilder("距");
        int length = ssb.length();
        ssb.setSpan(new TextAppearanceSpan(null, 0, WindowUtil.dip2px(this, 12), ColorStateList.valueOf(Color.parseColor("#333333")), null), 0, length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.append("1.0");
        ssb.setSpan(new TextAppearanceSpan(null, 0, WindowUtil.dip2px(this, 28),  ColorStateList.valueOf(Color.parseColor("#333333")), null), length, ssb.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        length = ssb.length();
        ssb.append("公里");
        ssb.setSpan(new TextAppearanceSpan(null, 0, WindowUtil.dip2px(this, 12), ColorStateList.valueOf(Color.parseColor("#333333")), null), length, ssb.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.append("\n全程").append("23.4").append("公里");
        return ssb;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        binding.gbvTest.stopGradientAnimation();
    }
}
