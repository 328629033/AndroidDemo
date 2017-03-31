package com.demo.android.databinding.list.item;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.demo.android.R;
import com.demo.android.databinding.list.MultiTypeAdapter;
import com.demo.android.databinding.list.model.ImageModel;
import com.squareup.picasso.Picasso;

/**
 * Created by herr.wang on 2017/3/13.
 */

public class ImageItem extends BaseItem {

    @Override
    public int getLayout() {
        return R.layout.item_list_image;
    }

    private final ImageModel model;
    private final MultiTypeAdapter adapter;

    public ImageItem(ImageModel model, MultiTypeAdapter adapter){
        this.model = model;
        this.adapter = adapter;
    }

    public String getImageUrl(){
        return "http://192.168.1.192/pics//g/20170223202922815_0.jpg";
    }

    public String getDesc(){
        return model.desc;
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error){
        Picasso.with(view.getContext()).load(url).error(error).into(view);
    }

    public void doClick(Context context){
        ((Activity)context).finish();
    }

}
