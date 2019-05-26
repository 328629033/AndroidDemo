package com.demo.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by herr.wang on 2017/7/21.
 */

public class FlotingView extends LinearLayout {
    public FlotingView(Context context) {
        this(context, null);
    }
    public FlotingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public FlotingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        View.inflate(context, R.layout.layout_floating, this);
//        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_floating, this, true);
    }
//    FloatingBinding binding;
//    Floating floating;
//
//    public void setEntity(Floating floating){
//        this.floating = floating;
//        binding.setEntity(floating);
//
//    }

}
