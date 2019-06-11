package com.chen.fakevibrato;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.chen.fakevibrato.base.BaseActivity;
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.ui.home.contract.MainContract;
import com.chen.fakevibrato.ui.home.presenter.MainPresenter;
import com.chen.fakevibrato.ui.home.view.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIPagerAdapter;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.viewPager)
    QMUIViewPager viewPager;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private MyPagerAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_attention:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_release:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_user:
                    viewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected int getLayoutId() {
        QMUIStatusBarHelper.translucent(this);
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initView() {

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        adapter = new MyPagerAdapter(getSupportFragmentManager(),mFragments);
        viewPager.setAdapter(adapter);
        viewPager.setSwipeable(false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {


    }
}
