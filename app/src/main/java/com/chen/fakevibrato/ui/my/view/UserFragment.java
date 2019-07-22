package com.chen.fakevibrato.ui.my.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseFragment;
import com.chen.fakevibrato.bean.SwipeBean;
import com.chen.fakevibrato.listener.AppBarStateChangeListener;
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.ui.home.view.HomeListFragment;
import com.chen.fakevibrato.ui.my.contract.UserContract;
import com.chen.fakevibrato.ui.my.presenter.UserPresenter;
import com.chen.fakevibrato.widget.anim.AnimtorUtils;
import com.chen.fakevibrato.widget.emojiview.EmojiconTextView;
import com.daimajia.swipe.SwipeLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by CHEN on 2019/7/18
 * @email 188669@163.com
 */
public class UserFragment extends BaseFragment<UserPresenter> implements UserContract.View {
    @BindView(R.id.ivBackGround)
    ImageView ivBackGround;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tablayout)
    QMUITabSegment tablayout;
    @BindView(R.id.mCollapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.ivHead)
    QMUIRadiusImageView ivHead;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.tvFriend)
    ImageView tvFriend;
    @BindView(R.id.tvName)
    EmojiconTextView tvName;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.tvIntroduction)
    EmojiconTextView tvIntroduction;
    @BindView(R.id.tvData)
    TextView tvData;
    @BindView(R.id.tvPraise)
    TextView tvPraise;
    @BindView(R.id.tvAttention)
    TextView tvAttention;
    @BindView(R.id.tvFans)
    TextView tvFans;
    @BindView(R.id.llTakePhoto)
    LinearLayout llTakePhoto;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.tvNameTool)
    TextView tvNameTool;
    @BindView(R.id.ivMore)
    ImageView ivMore;
    @BindView(R.id.viewPager)
    QMUIViewPager viewPager;

    private String[] mTitles = new String[]{"作品", "动态", "喜欢"};
    //appBarLayout 上一次状态
    private AppBarStateChangeListener.State state = AppBarStateChangeListener.State.EXPANDED;

    private MyPagerAdapter adapter;
    @Override
    protected int setView() {
        return R.layout.fragment_user;
        //        return R.layout.fragment_user_test;
    }

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initView(View view) {
        CollapsingToolbarLayout.LayoutParams lp = new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.height = QMUIDisplayHelper.getActionBarHeight(getActivity()) + QMUIDisplayHelper.getStatusBarHeight(getActivity());
        toolbar.setLayoutParams(lp);
        toolbar.setPadding(0, QMUIDisplayHelper.getStatusBarHeight(getActivity()), 0, 0);

        ConstraintLayout.LayoutParams lp1 = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.height = QMUIDisplayHelper.getActionBarHeight(getActivity()) + QMUIDisplayHelper.getStatusBarHeight(getActivity());
        tvNameTool.setLayoutParams(lp1);
        tvNameTool.setPadding(0, QMUIDisplayHelper.getStatusBarHeight(getActivity()), 0, 0);

//        viewPager.setPadding(0, 0, 0, QMUIDisplayHelper.getStatusBarHeight(getActivity()));

        ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
        mFragments.add(new UserVideoListFragment());
        mFragments.add(new UserVideoListFragment());
        mFragments.add(new UserVideoListFragment());
        adapter = new MyPagerAdapter(getChildFragmentManager(), mFragments);
        viewPager.setAdapter(adapter);

        for (int i = 0; i < mTitles.length; i++) {
            SpannableString sp = new SpannableString(mTitles[i]);
            sp.setSpan(new StyleSpan(Typeface.BOLD), 0, sp.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            QMUITabSegment.Tab tab = new QMUITabSegment.Tab(sp);
            tab.setText(sp);
            tab.setTextColor(Color.parseColor("#A9A9A9"), Color.WHITE);
            tablayout.addTab(tab);
        }
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.mipmap.indicator);
        tablayout.setIndicatorDrawable(drawable);
        tablayout.setHasIndicator(true);  //是否需要显示indicator
        tablayout.setIndicatorPosition(false);//true 时表示 indicator 位置在 Tab 的上方, false 时表示在下方
        tablayout.setIndicatorWidthAdjustContent(false);//设置 indicator的宽度是否随内容宽度变化
        tablayout.setupWithViewPager(viewPager, false);
        tablayout.notifyDataChanged();
        tablayout.selectTab(0, true, true);
        EventBus.getDefault().post(new SwipeBean(viewPager.getCurrentItem()));
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

    private void initListener() {

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State i) {
                if (i == State.COLLAPSED) {
                    AnimtorUtils.alpha(tvNameTool, 0, 1, 500, 0).start();
                } else {
                    if (state == State.COLLAPSED) {
                        AnimtorUtils.alpha(tvNameTool, 1, 0, 500, 0).start();
                    }
                }
                state = i;
            }
        });
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                super.onHeaderMoving(header, isDragging, percent, offset, headerHeight, maxDragHeight);
                //头部背景图缩放
                ivBackGround.setScaleX(percent / 10 + 1);
                ivBackGround.setScaleY(percent / 10 + 1);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                SwipeBean swipeBean = new SwipeBean(position);
                swipeBean.setDragEdge(SwipeLayout.DragEdge.Right);
                EventBus.getDefault().post(swipeBean);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.ivHead, R.id.tvEdit, R.id.tvFriend, R.id.llTakePhoto, R.id.ivMore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivHead:
                break;
            case R.id.tvEdit:
                break;
            case R.id.tvFriend:
                break;
            case R.id.llTakePhoto:
                break;
            case R.id.ivMore:       //
                SwipeBean swipeBean = new SwipeBean();
                swipeBean.setOpen(true);
                swipeBean.setDragEdge(SwipeLayout.DragEdge.Right);
                EventBus.getDefault().post(swipeBean);
                break;
        }
    }


}
