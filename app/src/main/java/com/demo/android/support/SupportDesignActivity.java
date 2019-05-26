package com.demo.android.support;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.android.R;
import com.demo.android.databinding.ActivitySupportDesignBinding;

/**
 * Created by herr.wang on 2017/5/5.
 */

public class SupportDesignActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySupportDesignBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_support_design);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, new ContentFragment()).commit();
    }
}
