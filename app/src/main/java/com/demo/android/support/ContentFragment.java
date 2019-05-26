package com.demo.android.support;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.android.R;
import com.demo.android.databinding.FgContentBinding;

/**
 * Created by herr.wang on 2017/5/11.
 */

public class ContentFragment extends Fragment {
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    CoordinatorLayout rootLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FgContentBinding binding = DataBindingUtil.inflate(inflater, R.layout.fg_content, container, false);
        rootLayout = binding.clContainer;
        toolbar = binding.tbTitle;
        floatingActionButton = binding.fab;
        floatingActionButton.setOnClickListener(onClickListener);

        toolbar.setTitle("首页");
        return binding.getRoot();
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fab:
                    Snackbar.make(rootLayout, "I am fine.", Snackbar.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
