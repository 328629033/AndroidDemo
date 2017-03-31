package com.demo.android.databinding.list;

import com.demo.android.R;
import com.demo.android.databinding.list.model.ImageModel;
import com.demo.android.databinding.list.model.TextModel;

import java.util.Random;

/**
 * Created by herr.wang on 2017/3/13.
 */

public class ListPresenter {
    private MultiTypeAdapter adapter;
    public ListPresenter(){
        adapter = new MultiTypeAdapter();
    }

    public MultiTypeAdapter getAdapter(){
        return adapter;
    }

    public void addItem(){
        adapter.addItem(getItem());
    }

    private MultiTypeAdapter.IItem getItem(){
        int random = new Random().nextInt(10);
        if(random % 2 == 0){
            ImageModel imageModel = new ImageModel();
            imageModel.desc = String.format("这是第%s张图", random);
            imageModel.url = R.drawable.flower;
            return imageModel.createItem(adapter);
        }else{
            TextModel textModel = new TextModel();
            textModel.text = String.format("hello, I am ", random);
            return textModel.createItem(adapter);
        }
    }

}
