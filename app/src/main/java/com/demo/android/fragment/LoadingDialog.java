package com.demo.android.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.demo.android.R;

/**
 * Created by herr.wang on 2017/7/8.
 */

public class LoadingDialog extends Dialog {
    private ImageView contentView;
    public LoadingDialog(Context context) {
        this(context, 0);
    }

    public LoadingDialog(Context context, int themeId){
        super(context, R.style.LoadingDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = new ImageView(getContext());
        setContentView(contentView);
//        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        contentView.setImageResource(R.drawable.drawable_loading);
        AnimationDrawable drawable = (AnimationDrawable) contentView.getDrawable();
        drawable.start();
    }

    @Override
    public void dismiss() {
        AnimationDrawable drawable = (AnimationDrawable) contentView.getDrawable();
        drawable.stop();
        super.dismiss();
    }
}
