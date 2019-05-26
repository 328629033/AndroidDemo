package com.demo.android.concurrent;

import com.demo.android.BR;
import com.demo.android.R;
import com.demo.android.common.CommonRecyclerViewAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by herr.wang on 2017/4/11.
 */

public class Product implements CommonRecyclerViewAdapter.IItem {
    public static AtomicInteger seq = new AtomicInteger(0);
    public long time;
    public String content;
    public int checkCount;

    public Product(){
        seq.addAndGet(1);
    }

    public Product(String content){
        super();
        this.content = content;
    }

    public String getShowTime(){
        DateFormat df = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
        return df.format(new Date(time));
    }

    @Override
    public int getLayout() {
        return R.layout.item_product;
    }

    @Override
    public int getVariableId() {
        return BR.product;
    }
}
