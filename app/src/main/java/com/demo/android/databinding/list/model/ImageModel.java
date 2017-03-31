package com.demo.android.databinding.list.model;

import com.demo.android.databinding.list.MultiTypeAdapter;
import com.demo.android.databinding.list.item.ImageItem;

/**
 * Created by herr.wang on 2017/3/13.
 */

public class ImageModel extends BaseModel{

    public ImageModel(){

    }

    public int url;
    public String desc;

    @Override
    public MultiTypeAdapter.IItem createItem(MultiTypeAdapter adapter) {
        return new ImageItem(this, adapter);
    }
}
