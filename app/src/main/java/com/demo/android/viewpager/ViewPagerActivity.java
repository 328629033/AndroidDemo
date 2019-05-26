package com.demo.android.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.demo.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/7/14.
 */

public class ViewPagerActivity extends AppCompatActivity {
    ViewPager viewPager;
    List<Fragment> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        viewPager = (ViewPager) findViewById(R.id.vp_home);
        list = new ArrayList<>();
        list.add(new ViewPagerFragment());
        list.add(new ViewPagerFragment());
        Adapter adapter = new Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }



    private class Adapter extends FragmentPagerAdapter{

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
