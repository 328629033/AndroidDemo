package com.demo.android.databinding.list.item;

import com.demo.android.BR;
import com.demo.android.databinding.list.MultiTypeAdapter;

/**
 * Created by herr.wang on 2017/3/13.
 */

public abstract class BaseItem implements MultiTypeAdapter.IItem {

    @Override
    public int getVariableId() {
        return BR.item;
    }
}
