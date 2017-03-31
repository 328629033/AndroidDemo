package com.demo.android.databinding.list.model;

import com.demo.android.databinding.list.MultiTypeAdapter;

/**
 * Created by herr.wang on 2017/3/13.
 */

public abstract class BaseModel {
    public abstract MultiTypeAdapter.IItem createItem(MultiTypeAdapter adapter);
}
