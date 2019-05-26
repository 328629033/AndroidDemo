package com.demo.android;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by herr.wang on 2017/7/12.
 */

public class FloatViewManager {
    private static FloatViewManager instance;
    private boolean isShowing;
    private WindowManager wm;

    private FloatViewManager() {
        wm = (WindowManager) MyApplication.context.getSystemService(Context.WINDOW_SERVICE);
    }

    public static FloatViewManager getInstance() {
        if (instance == null) {
            instance = new FloatViewManager();
        }
        return instance;
    }

    WindowManager.LayoutParams lp;

    private void getLp() {
        lp = new WindowManager.LayoutParams();
        lp.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
//            lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.flags |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        lp.format = PixelFormat.RGBA_8888;
        lp.type = WindowManager.LayoutParams.TYPE_TOAST;
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        lp.windowAnimations = R.style.Popup;
    }

    private ImageView ivFloat;
    private Context context;
    View view;

    public void setContext(Context context) {
        this.context = context;
    }

    public void showView() {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_order_preview_dialog, null);
            if (lp == null) {
                getLp();
            }
            wm.addView(view, lp);
        } else {
            wm.addView(view, lp);
        }
    }

    public void show() {
        System.out.println("show.....");

        if (ivFloat == null) {
            ivFloat = new ImageView(context);
            ivFloat.setImageResource(R.drawable.head);
            ivFloat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MyApplication.context, "click", Toast.LENGTH_SHORT).show();
                }
            });
            ivFloat.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        wm.removeView(ivFloat);
                        isShowing = false;
                        return true;
                    }
                    return false;
                }
            });
            if (lp == null) {
                getLp();
            }
            ivFloat.setVisibility(View.VISIBLE);
            wm.addView(ivFloat, lp);
            if(ivFloat.getParent() != null){
                System.out.println("added ivFloat...");

            }
        } else {
            if (ivFloat.getParent() != null) {
                ivFloat.setVisibility(View.VISIBLE);
            } else {
                wm.addView(ivFloat, lp);
            }
        }
    }


    FlotingView flotingView;

    public void show2() {
        if (flotingView == null) {
            flotingView = new FlotingView(context);
//            flotingView.setEntity(new Floating("hello world", 10000));
            if (lp == null) {
                getLp();
            }
            wm.addView(flotingView, lp);

            System.out.println("added...");
        } else {
            wm.addView(flotingView, lp);
        }
    }

    public void hide() {
        System.out.println("hide...");
        if (ivFloat != null) {
            ivFloat.setVisibility(View.GONE);
        }
    }

    public void remove() {
        wm.removeView(flotingView);
        show2();
    }

    public void removeView() {
        if(ivFloat.getParent() != null){
            System.out.println("I have a parent." );
        }
        wm.removeView(ivFloat);
        show();
//        showView();
    }
}
