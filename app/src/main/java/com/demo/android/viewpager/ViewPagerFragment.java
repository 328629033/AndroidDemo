package com.demo.android.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/7/14.
 */

public class ViewPagerFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fg_viewpager, container, false);
        tabLayout = (TabLayout) contentView.findViewById(R.id.tl_trip);
        viewPager = (ViewPager) contentView.findViewById(R.id.vp_trip);
        list = new ArrayList<>();
        list.add(new ChildFragment());
        list.add(new ChildFragment());
        Adapter adapter = new Adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        System.out.println("onCreateView....");
        return contentView;
    }

    String[] titles = {"一个","两个"};

    class Adapter extends FragmentPagerAdapter {

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

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
