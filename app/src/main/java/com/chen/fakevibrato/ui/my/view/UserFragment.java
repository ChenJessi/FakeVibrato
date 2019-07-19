package com.chen.fakevibrato.ui.my.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseFragment;
import com.chen.fakevibrato.ui.my.contract.UserContract;
import com.chen.fakevibrato.ui.my.presenter.UserPresenter;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.emojiview.EmojiconTextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

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
    @BindView(R.id.iv)
    TextView iv;
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

    private String[] mTitles = new String[]{"作品", "动态", "喜欢"};

    @Override
    protected int setView() {
        return R.layout.fragment_user;
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
        //tablayout.setupWithViewPager(viewPager, false);
        tablayout.notifyDataChanged();
        tablayout.selectTab(0, true, true);

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

        coordinatorLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                MyLog.d("scrollX : " + scrollX + " scrollY " + scrollY + "  oldScrollX " + oldScrollX + "  oldScrollY" + oldScrollY);

            }
        });
        //        coordinatorLayout.stopNestedScroll();
    }
}
