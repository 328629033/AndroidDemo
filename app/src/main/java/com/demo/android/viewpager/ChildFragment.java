package com.demo.android.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.android.R;
import com.demo.android.common.CommonRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/7/14.
 */

public class ChildFragment extends Fragment{
    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fg_child, container, false);
        refreshLayout = (SwipeRefreshLayout) contentView;
        recyclerView = (RecyclerView) contentView.findViewById(R.id.list);
        List<CommonRecyclerViewAdapter.IItem> itemList = new ArrayList<>();
        CommonRecyclerViewAdapter adapter = new CommonRecyclerViewAdapter(itemList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return contentView;
    }
}
