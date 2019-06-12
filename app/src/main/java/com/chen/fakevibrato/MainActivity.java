package com.chen.fakevibrato;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.chen.fakevibrato.base.BaseActivity;
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.ui.home.contract.MainContract;
import com.chen.fakevibrato.ui.home.presenter.MainPresenter;
import com.chen.fakevibrato.ui.home.view.HomeFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.viewPager)
    QMUIViewPager viewPager;
    @BindView(R.id.mTabLayout)
    CommonTabLayout  mTabLayout;

    private MyPagerAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();


    @Override
    protected int getLayoutId() {
        QMUIStatusBarHelper.translucent(this);
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }
    String[] mTitles = new String[]{"首页","首页","首页","首页","首"};
    @Override
    protected void initView() {
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
//        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        adapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(adapter);
        viewPager.setSwipeable(false);


//        mTabSegment.setHasIndicator(true);
//        mTabSegment.setIndicatorPosition(false);
//        mTabSegment.setIndicatorWidthAdjustContent(true);
//        mTabSegment.setDefaultSelectedColor(getColor(R.color.white));
//        mTabSegment.setDefaultNormalColor(getColor(R.color.gray1));
//        mTabSegment.addTab(new QMUITabSegment.Tab(getString(R.string.tabSegment_item_1_title)));
//        mTabSegment.addTab(new QMUITabSegment.Tab(getString(R.string.tabSegment_item_2_title)));
////        mTabSegment.addTab(new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.mipmap.ic_launcher),null,"",true));
//        mTabSegment.addTab(new QMUITabSegment.Tab(getString(R.string.tabSegment_item_4_title)));
//        mTabSegment.addTab(new QMUITabSegment.Tab(getString(R.string.tabSegment_item_5_title)));
//        mTabSegment.setupWithViewPager(viewPager,false);
//        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

        for (int i = 0;i < mTitles.length; i++){
            int finalI = i;
            mTabEntities.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return mTitles[finalI];
                }
                @Override
                public int getTabSelectedIcon() {
                    return 0;
                }

                @Override
                public int getTabUnselectedIcon() {
                    return 0;
                }
            }) ;
        }

        mTabLayout.setTabData(mTabEntities);

    }

    @Override
    protected void initListener() {
        mTabLayout.setIndicatorWidth(82);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                TextView textView = mTabLayout.getTitleView(position);
                String text = textView.getText().toString().trim();
                Paint textPaint = textView.getPaint();

                int textPaintWidth = (int) textPaint.measureText(text);

//                mTabLayout.setIndicatorWidth( textPaintWidth- textView.getPaddingLeft());
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void initData() {


    }

}
