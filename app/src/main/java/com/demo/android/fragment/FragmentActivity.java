package com.demo.android.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.android.R;

import java.util.Random;

/**
 * Created by herr.wang on 2017/7/7.
 */

public class FragmentActivity extends AppCompatActivity implements FragmentCallback {
    Argument argument;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        argument = new Argument();
        argument.name = "activity";
//        showPopup();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, MyFragment.newInstance(argument),"me").commit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Argument argument = new Argument();
                argument.name = "fragment";
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, MyFragment.newInstance(argument),"you").commit();
            }
        }, 5000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showPopup();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    public void onClick() {
        String text = "this is pop " + new Random().nextInt(10);
        if(tvPopup != null){
            tvPopup.setText(text);
        }
        showPopup();
        Fragment fg = getSupportFragmentManager().findFragmentByTag("me");
        argument.name = "pig";
        if(fg instanceof MyFragment){
            ((MyFragment)fg).toast();
        }
    }

    TextView tvPopup;
    private void showPopup(){

        if(tvPopup == null){
            tvPopup = new TextView(this);
            tvPopup.setText("this is a pop");
            tvPopup.setWidth(300);
            tvPopup.setHeight(300);
            tvPopup.setGravity(Gravity.CENTER);
            tvPopup.setBackgroundColor(Color.YELLOW);
            tvPopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FragmentActivity.this, "click", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                }
            });
        }
        if(popupWindow == null) {
            popupWindow = new PopupWindow(tvPopup, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setAnimationStyle(R.style.Popup);
        }


        View rootView = ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
        popupWindow.showAtLocation(rootView, Gravity.TOP, 0, 0);
    }


}
