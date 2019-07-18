package com.chen.fakevibrato.ui.home.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chen.fakevibrato.MainActivity;
import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseFragment;
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.ui.home.contract.HomeContract;
import com.chen.fakevibrato.ui.home.presenter.HomePresenter;
import com.chen.fakevibrato.utils.DisplayUtils;
import com.chen.fakevibrato.utils.MyLog;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页fragment
 * @author Created by CHEN on 2019/6/11
 * @email 188669@163.com
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {
    @BindView(R.id.tvRandom)
    TextView tvRandom;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.ivLive)
    ImageView ivLive;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.mTabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.tablayout)
    QMUITabSegment tablayout;

    private MyPagerAdapter adapter;

    String[] mTitles = new String[]{"关注", "推荐"};

    @Override
    protected int setView() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView(View view) {
        ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
        mFragments.add(new HomeListFragment());
        mFragments.add(new HomeListFragment());
        adapter = new MyPagerAdapter(getChildFragmentManager(), mFragments);
        viewPager.setAdapter(adapter);
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
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
            });

            SpannableString sp = new SpannableString(mTitles[i]);
            sp.setSpan(new StyleSpan(Typeface.BOLD),0, sp.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            QMUITabSegment.Tab tab = new QMUITabSegment.Tab(sp);
            tab.setText(sp);
            tab.setTextColor(Color.parseColor("#A9A9A9"), Color.WHITE);
            tablayout.addTab(tab);
        }
//        mTabLayout.setTabData(mTabEntities);

        tablayout.setHasIndicator(true);  //是否需要显示indicator
        tablayout.setIndicatorPosition(false);//true 时表示 indicator 位置在 Tab 的上方, false 时表示在下方
        tablayout.setIndicatorWidthAdjustContent(true);//设置 indicator的宽度是否随内容宽度变化
        tablayout.setupWithViewPager(viewPager,false);
        tablayout.setupWithViewPager(viewPager,false);
        tablayout.notifyDataChanged();
        tablayout.selectTab(0,true,true);

        initListener();
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();

    }

    @Override
    protected void Load() {

    }

    @OnClick({R.id.tvRandom, R.id.ivSearch, R.id.ivLive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvRandom:
                break;
            case R.id.ivSearch:
                break;
            case R.id.ivLive:
                break;
        }
    }

    private void initListener() {

        mTabLayout.setIndicatorWidth(30);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        tablayout.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
//                viewPager.setCurrentItem(index);
            }
            @Override
            public void onTabUnselected(int index) {

            }
            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
////                mTabLayout.setCurrentTab(position);
//                tablayout.selectTab(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }
}
