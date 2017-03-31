package com.demo.android.databinding;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.demo.android.databinding.list.DBListActivity;

/**
 * Created by herr.wang on 2017/3/9.
 */

public class EventHandler {
    public void onNameClick(View view){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1000);
        view.startAnimation(alphaAnimation);
    }

    public boolean onNameLongClick(View view, Context context){
        ((TextView)view).setText("hello");
        Intent it = new Intent(context, DBListActivity.class);
        context.startActivity(it);
        return true;
    }

    public void onChecked(View view, Task task, boolean isChecked){
        if(isChecked){
            task.run();
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
    }
}
