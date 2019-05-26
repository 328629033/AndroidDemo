package com.demo.android.recyclerview;

import android.view.ViewGroup;

import com.demo.android.BR;
import com.demo.android.R;
import com.demo.android.common.CommonRecyclerViewAdapter;

/**
 * Created by herr.wang on 2017/7/12.
 */

public class EmptyPlaceItem implements CommonRecyclerViewAdapter.IItem {
    public int height;
    ViewGroup.LayoutParams lp;

    public EmptyPlaceItem(int height){
        this.height = height;
        lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public ViewGroup.LayoutParams getLp(){
        lp.height = height;
        return lp;
    }
    @Override
    public int getLayout() {
        return R.layout.item_empty;
    }

    @Override
    public int getVariableId() {
        return BR.item;
    }
}
