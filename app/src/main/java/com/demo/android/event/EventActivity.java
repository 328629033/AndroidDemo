package com.demo.android.event;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.android.R;
import com.demo.android.databinding.ActivityEventBinding;

/**
 * Created by herr.wang on 2017/3/23.
 */

public class EventActivity extends AppCompatActivity {
    TextView tvText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEventBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_event);
        binding.setActivity(this);
        tvText = binding.tvText;
        Log.i("","event activity.");
    }

    public void doClick(View view){
        System.out.println("doClick..");
        if(view.getId() == R.id.tv_text){
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        }else{
            System.out.println("top clicked.");
        }
    }
}
