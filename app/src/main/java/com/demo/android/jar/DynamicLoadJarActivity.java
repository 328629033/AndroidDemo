package com.demo.android.jar;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.android.R;
import com.demo.android.databinding.ActivityLoadJarBinding;

/**
 * Created by herr.wang on 2017/5/12.
 */

public class DynamicLoadJarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoadJarBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_load_jar);
        DynamicLoader loader = new DynamicLoader();
        loader.load(this);
    }
}
