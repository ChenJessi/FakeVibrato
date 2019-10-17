package com.chen.fakevibrato.widget;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by CHEN on 2019/6/11
 * @email 188669@163.com
 */
public class MyStatePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private FragmentManager fm;
    public MyStatePagerAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
