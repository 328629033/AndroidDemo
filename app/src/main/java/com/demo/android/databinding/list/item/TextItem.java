package com.demo.android.databinding.list.item;

import com.demo.android.R;
import com.demo.android.databinding.list.MultiTypeAdapter;
import com.demo.android.databinding.list.model.TextModel;

/**
 * Created by herr.wang on 2017/3/13.
 */

public class TextItem extends BaseItem {
    @Override
    public int getLayout(){
        return R.layout.item_list_text;
    }

    public TextItem(TextModel textModel, MultiTypeAdapter adapter){
        this.model = textModel;
        this.adapter = adapter;
    }

    private final MultiTypeAdapter adapter;
    private final TextModel model;

    public String getText(){
        return model.text;
    }
}
