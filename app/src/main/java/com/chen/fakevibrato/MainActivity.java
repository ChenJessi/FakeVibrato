package com.chen.fakevibrato;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chen.fakevibrato.base.BaseActivity;
import com.chen.fakevibrato.bean.SwipeBean;
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.ui.home.contract.MainContract;
import com.chen.fakevibrato.ui.home.presenter.MainPresenter;
import com.chen.fakevibrato.ui.home.view.HomeFragment;
import com.chen.fakevibrato.ui.message.view.MessageFragment;
import com.chen.fakevibrato.ui.my.view.EditMessageActivity;
import com.chen.fakevibrato.ui.my.view.UserFragment;
import com.chen.fakevibrato.ui.samecity.view.SameCityFragment;
import com.chen.fakevibrato.utils.DisplayUtils;
import com.chen.fakevibrato.utils.MyLog;
import com.daimajia.swipe.SwipeLayout;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.viewPager)
    QMUIViewPager viewPager;
    @BindView(R.id.mTabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.ivBottom)
    ImageView ivBottom;
    @BindView(R.id.swipeLayout)
    SwipeLayout swipeLayout;

    @BindView(R.id.tvCard)
    TextView tvCard;
    @BindView(R.id.tvPentagram)
    TextView tvPentagram;
    @BindView(R.id.tvWallet)
    TextView tvWallet;
    @BindView(R.id.tvOrder)
    TextView tvOrder;
    @BindView(R.id.tvService)
    TextView tvService;
    @BindView(R.id.tvShop)
    TextView tvShop;
    @BindView(R.id.tvLifeOrder)
    TextView tvLifeOrder;
    @BindView(R.id.tvDOU)
    TextView tvDOU;
    @BindView(R.id.tvFlow)
    TextView tvFlow;
    @BindView(R.id.llService)
    LinearLayout llService;
    @BindView(R.id.tvCoupon)
    TextView tvCoupon;
    @BindView(R.id.tvMinor)
    TextView tvMinor;
    @BindView(R.id.tvSetting)
    TextView tvSetting;
    @BindView(R.id.tvApplets)
    TextView tvApplets;
    @BindView(R.id.tvHeadline)
    TextView tvHeadline;
    @BindView(R.id.llApplets)
    LinearLayout llApplets;
    @BindView(R.id.side_right)
    ScrollView sideRight;
    @BindView(R.id.viewService)
    View viewService;


    private MyPagerAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    String[] mTitles = new String[]{"首页", "同城", "", "消息", "我"};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initView() {
        initSideRight();
        mFragments.add(new HomeFragment());
        mFragments.add(new SameCityFragment());
        mFragments.add(new Fragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new UserFragment());
        adapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(adapter);
        viewPager.setSwipeable(false);

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
        }
        mTabLayout.setTabData(mTabEntities);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, findViewById(R.id.random_shoot));
        swipeLayout.addDrag(SwipeLayout.DragEdge.Right, findViewById(R.id.random_shoot));
        swipeLayout.setLeftSwipeEnabled(true);
        swipeLayout.setRightSwipeEnabled(false);
        swipeLayout.setClickToClose(true);

    }

    @Override
    protected void initListener() {
        TextView textView = mTabLayout.getTitleView(0);
        String text = textView.getText().toString().trim();
        TextPaint textPaint = textView.getPaint();
        int textPaintWidth = (int) textPaint.measureText(text);
        mTabLayout.setIndicatorWidth(DisplayUtils.px2dp(MainActivity.this, textPaintWidth));
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                MyLog.d("viewPager: " + position + "   " + viewPager);
                viewPager.setCurrentItem(position);
                TextView textView = mTabLayout.getTitleView(position);
                String text = textView.getText().toString().trim();
                TextPaint textPaint = textView.getPaint();
                int textPaintWidth = (int) textPaint.measureText(text);
                mTabLayout.setIndicatorWidth(DisplayUtils.px2dp(MainActivity.this, textPaintWidth));
                if (position == 0) {     //首页
                    swipeLayout.addDrag(SwipeLayout.DragEdge.Left, findViewById(R.id.random_shoot));
                    swipeLayout.addDrag(SwipeLayout.DragEdge.Right, findViewById(R.id.random_shoot));
                    if (childPosition == 0) {
                        swipeLayout.setLeftSwipeEnabled(true);
                        swipeLayout.setRightSwipeEnabled(false);
                    } else if (childPosition == 1) {
                        swipeLayout.setLeftSwipeEnabled(false);
                        swipeLayout.setRightSwipeEnabled(true);
                    }
                } else if (position == 4) {   //我的
                    MyLog.d("childPosition test");
                    swipeLayout.addDrag(SwipeLayout.DragEdge.Right, findViewById(R.id.side_right));
                    swipeLayout.setLeftSwipeEnabled(false);
                    if (childPosition == 2) {
                        swipeLayout.setRightSwipeEnabled(true);
                    } else {
                        swipeLayout.setRightSwipeEnabled(false);
                    }
                } else {
                    swipeLayout.setLeftSwipeEnabled(false);
                    swipeLayout.setRightSwipeEnabled(false);
                }

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    private int childPosition = 0;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void swipeStatus(SwipeBean swipeBean) {
        childPosition = swipeBean.getPosition();
        MyLog.d("viewPager test : " + childPosition + "   " + viewPager);
        MyLog.d("positionces : " + childPosition);
        if (viewPager.getCurrentItem() == 0) {
            swipeLayout.addDrag(SwipeLayout.DragEdge.Left, findViewById(R.id.random_shoot));
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right, findViewById(R.id.random_shoot));
            if (childPosition == 0) {
                swipeLayout.setLeftSwipeEnabled(true);
                swipeLayout.setRightSwipeEnabled(false);
            } else if (childPosition == 1) {
                swipeLayout.setLeftSwipeEnabled(false);
                swipeLayout.setRightSwipeEnabled(true);
            } else {
                swipeLayout.setLeftSwipeEnabled(false);
                swipeLayout.setRightSwipeEnabled(false);
            }
        } else if (viewPager.getCurrentItem() == 4) {
            swipeLayout.setLeftSwipeEnabled(false);
            if (childPosition == 2 || swipeBean.isOpen()) {

                swipeLayout.addDrag(SwipeLayout.DragEdge.Right, findViewById(R.id.side_right));
                MyLog.d("childPosition : " + childPosition + "   " + swipeBean.isOpen());
                if (childPosition == 2) {
                    swipeLayout.setRightSwipeEnabled(true);
                } else {
                    swipeLayout.setRightSwipeEnabled(false);
                }
                if (swipeBean.isOpen()) {
                    if (swipeLayout.getOpenStatus() == SwipeLayout.Status.Open) {
                        swipeLayout.close(true);
                    } else {
                        swipeLayout.open(true, swipeBean.getDragEdge());
                    }
                }
            } else {
                swipeLayout.setRightSwipeEnabled(false);
            }
        }
    }

    @OnClick(R.id.ivBottom)
    public void onViewClicked() {
//       startActivity(new Intent(MainActivity.this, EditMessageActivity.class));
//       startActivity(new Intent(MainActivity.this, EmojiActivity.class));
//       startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }

    @Override
    public void onBackPressed() {
        if (swipeLayout.getOpenStatus() == SwipeLayout.Status.Open) {
            swipeLayout.close();
            return;
        }
        super.onBackPressed();
    }


    /**
     * 右滑view
     * side_right
     */
    private void initSideRight() {


    }

    @OnClick({R.id.tvCard, R.id.tvPentagram, R.id.tvShop, R.id.tvService, R.id.tvApplets})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvCard:

                break;
            case R.id.tvPentagram:

                break;
            case R.id.tvShop:
                break;
            case R.id.tvService:   //服务
                if (llService.getVisibility() == View.GONE) {
                    llService.setVisibility(View.VISIBLE);
                    viewService.setVisibility(View.INVISIBLE);
                } else {
                    llService.setVisibility(View.GONE);
                    viewService.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.tvApplets:
                if (llApplets.getVisibility() == View.GONE) {
                    llApplets.setVisibility(View.VISIBLE);
                } else {
                    llApplets.setVisibility(View.GONE);
                }
                break;
        }
    }
}
