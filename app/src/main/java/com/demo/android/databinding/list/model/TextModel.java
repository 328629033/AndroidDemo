package com.demo.android.databinding.list.model;

import com.demo.android.databinding.list.MultiTypeAdapter;
import com.demo.android.databinding.list.item.TextItem;

/**
 * Created by herr.wang on 2017/3/13.
 */

public class TextModel extends BaseModel {
    @Override
    public MultiTypeAdapter.IItem createItem(MultiTypeAdapter adapter) {
        return new TextItem(this, adapter);
    }

    public TextModel(){
        text = "hello world.";
    }

    public String text;
}
