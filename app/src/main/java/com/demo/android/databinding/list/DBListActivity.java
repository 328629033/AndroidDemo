package com.demo.android.databinding.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.android.R;
import com.demo.android.databinding.ActivityDbListBinding;

/**
 * Created by herr.wang on 2017/3/13.
 */

public class DBListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDbListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_db_list);
        recyclerView = binding.rvList;
        presenter = new ListPresenter();
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(presenter.getAdapter());
        for(int i=0; i<10; i++){
            presenter.addItem();
        }
        presenter.getAdapter().notifyDataSetChanged();

    }
}
