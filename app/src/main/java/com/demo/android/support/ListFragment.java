package com.demo.android.support;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.android.R;
import com.demo.android.common.CommonRecyclerViewAdapter;
import com.demo.android.databinding.FgSupportListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/5/11.
 */

public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    CommonRecyclerViewAdapter adapter;
    List<CommonRecyclerViewAdapter.IItem> itemList;
    private String title;

    public static ListFragment newInstance(String title){
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FgSupportListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fg_support_list, container, false );
        recyclerView = binding.rvList;
        Bundle bundle = getArguments();
        if(bundle != null){
            title = bundle.getString("title");
        }
        itemList = new ArrayList<>();
        fillList();
        adapter = new CommonRecyclerViewAdapter(itemList, getContext());
        return binding.getRoot();
    }

    private void fillList(){
        for(int i= 0; i< 10; i++){
            SupportItem si = new SupportItem();
            si.title = title;
            si.content = "在按着固定节奏流逝的时光之中，既缺乏动人心魄的时间，也缺乏令人企羡的奇遇。";
            si.imgResource = (i%2==0 ? R.drawable.show_img1 : R.drawable.show_img2);
            itemList.add(si);
        }
    }
}
