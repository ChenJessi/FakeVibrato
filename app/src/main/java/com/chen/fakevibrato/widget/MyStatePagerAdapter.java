package com.chen.fakevibrato.widget;

import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.chen.fakevibrato.ui.home.view.HomeFragment;
import com.chen.fakevibrato.ui.message.view.MessageFragment;
import com.chen.fakevibrato.ui.my.view.UserFragment;
import com.chen.fakevibrato.ui.samecity.view.SameCityFragment;
import com.chen.fakevibrato.utils.MyLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by CHEN on 2019/6/11
 * @email 188669@163.com
 */
public class MyStatePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    HashMap<String, String> map = new HashMap<String, String>();
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
//    SparseArray
    public MyStatePagerAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
//        registeredFragments.put(0, mFragments.get(0));
        this.mFragments = mFragments;
//        for (int i = 0; i < mFragments.size(); i++) {
//            registeredFragments.put(i, mFragments.get(i));
//        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
//        switch (position) {
//            case 0:
//                return HomeFragment.newInstance();
//            case 1:
//                return SameCityFragment.newInstance();
//            case 2:
//                return new Fragment();
//            case 3:
//                return MessageFragment.newInstance();
//            case 4:
//                return UserFragment.newInstance();
//            default:
//                return null;
//        }

    }

//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        Fragment fragment = (Fragment) super.instantiateItem(container, position);
//        registeredFragments.put(position, fragment);
//        return fragment;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        registeredFragments.remove(position);
//        super.destroyItem(container, position, object);
//    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
