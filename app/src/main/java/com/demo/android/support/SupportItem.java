package com.demo.android.support;

import com.demo.android.BR;
import com.demo.android.R;
import com.demo.android.common.CommonRecyclerViewAdapter;

/**
 * Created by herr.wang on 2017/5/11.
 */

public class SupportItem implements CommonRecyclerViewAdapter.IItem {

    public int imgResource;
    public String title;
    public String content;

    @Override
    public int getLayout() {
        return R.layout.item_list_card;
    }

    @Override
    public int getVariableId() {
        return BR.item;
    }
}
