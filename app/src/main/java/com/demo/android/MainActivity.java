package com.demo.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.android.animation.AnimationActivity;
import com.demo.android.apt.AptActivity;
import com.demo.android.databinding.DataBindingActivity;
import com.demo.android.event.EventActivity;
import com.demo.android.selfview.ViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context context;
    private RecyclerView rvDemoList;
    private List<DemoBean> demos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        rvDemoList = (RecyclerView) findViewById(R.id.rv_demo_list);
        initDemo();
    }


    private void initDemo(){
        demos = new ArrayList<>();
        demos.add(getDemoBean("data-binding demo", DataBindingActivity.class));
        demos.add(getDemoBean("self-view demo", ViewActivity.class));
        demos.add(getDemoBean("animation demo", AnimationActivity.class));
        demos.add(getDemoBean("event demo", EventActivity.class));
        demos.add(getDemoBean("apt demo", AptActivity.class));
        rvDemoList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvDemoList.setAdapter(adapter);
    }

    private DemoBean getDemoBean(String demoName, Class demoClass){
        DemoBean demoBean = new DemoBean();
        demoBean.demoName = demoName;
        demoBean.demoClass = demoClass;
        return demoBean;
    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextViewHolder holder = new TextViewHolder(new TextView(context));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class clazz = ((DemoBean)v.getTag()).demoClass;
                    Intent it = new Intent(context, clazz);
                    startActivity(it);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            holder.itemView.setTag(demos.get(position));
            ((TextView)((TextViewHolder)holder).itemView).setText(demos.get(position).demoName);
        }

        @Override
        public int getItemCount() {
            return demos.size();
        }

        class TextViewHolder extends RecyclerView.ViewHolder{
            public TextViewHolder(View contentView){
                super(contentView);
            }
        }
    };
}
