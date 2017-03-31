package com.demo.android.animation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.android.CustomConfig;
import com.demo.android.R;

/**
 * Created by herr.wang on 2017/3/14.
 */

public class ConfigFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CustomConfig binding = DataBindingUtil.inflate(inflater, R.layout.fg_config, container, false);
        return binding.getRoot();
    }
}
