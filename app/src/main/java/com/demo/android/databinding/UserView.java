package com.demo.android.databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.android.R;

/**
 * Created by herr.wang on 2017/3/17.
 */

public class UserView extends LinearLayout {
    TextView tvName;
    public UserView(Context context) {
        this(context, null);
    }
    public UserView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        UserViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_user, this, true);
        tvName = binding.tvName;
    }

    public void setName(String name){
        tvName.setText(name);
    }

}
