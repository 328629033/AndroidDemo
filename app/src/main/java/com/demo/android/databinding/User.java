package com.demo.android.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.demo.android.BR;

/**
 * Created by herr.wang on 2017/3/9.
 */

public class User extends BaseObservable {
    private String name;
    private int age;
    public String url = "http://192.168.1.192/pics//g/20170223202922815_0.jpg";

    public Fruit fruit;

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
