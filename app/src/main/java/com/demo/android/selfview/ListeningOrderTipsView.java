package com.demo.android.selfview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.android.ListeningOrderTips;
import com.demo.android.R;


/**
 * Created by herr.wang on 2017/3/20.
 */

public class ListeningOrderTipsView extends RelativeLayout {
    private TextView tvTips;
    private GradientBackgroundView gbvListening;
    private TextView tvListeningType;
    public ListeningOrderTipsView(Context context) {
        this(context, null);
    }

    public ListeningOrderTipsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListeningOrderTipsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ListeningOrderTips binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_listening_order_tips, this, true);
        tvTips = binding.tvTips;
        gbvListening = binding.gbvListening;
        tvListeningType = binding.tvListeningType;
    }

    public void startGradientAnimation(){
        gbvListening.startGradientAnimation();
    }

    public void stopGradientAnimation(){
        gbvListening.stopGradientAnimation();
    }


}
