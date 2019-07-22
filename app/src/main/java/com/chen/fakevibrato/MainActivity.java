package com.chen.fakevibrato;

import android.content.Intent;
import android.text.TextPaint;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.chen.fakevibrato.base.BaseActivity;
import com.chen.fakevibrato.bean.SwipeBean;
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.ui.home.contract.MainContract;
import com.chen.fakevibrato.ui.home.presenter.MainPresenter;
import com.chen.fakevibrato.ui.home.view.HomeFragment;
import com.chen.fakevibrato.ui.home.view.HomeListFragment;
import com.chen.fakevibrato.ui.my.view.UserFragment;
import com.chen.fakevibrato.ui.my.view.UserVideoListFragment;
import com.chen.fakevibrato.utils.DisplayUtils;

import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.emojipanel.EmojiActivity;
import com.daimajia.swipe.SwipeLayout;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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

    private MyPagerAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    String[] mTitles = new String[]{"首页", "关注", "", "消息", "我"};

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

        mFragments.add(new HomeFragment());
        mFragments.add(new HomeListFragment());
        mFragments.add(new Fragment());
        mFragments.add(new HomeListFragment());
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
                MyLog.d("viewPager: "+position + "   "  +viewPager );
                viewPager.setCurrentItem(position);
                TextView textView = mTabLayout.getTitleView(position);
                String text = textView.getText().toString().trim();
                TextPaint textPaint = textView.getPaint();
                int textPaintWidth = (int) textPaint.measureText(text);
                mTabLayout.setIndicatorWidth(DisplayUtils.px2dp(MainActivity.this, textPaintWidth));
                if (position == 0){     //首页
                    if (childPosition == 0){
                        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, findViewById(R.id.random_shoot));
                        swipeLayout.setLeftSwipeEnabled(true);
                        swipeLayout.setRightSwipeEnabled(false);
                    }else if (childPosition == 1){
                        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, findViewById(R.id.random_shoot));
                        swipeLayout.setLeftSwipeEnabled(false);
                        swipeLayout.setRightSwipeEnabled(true);
                    }
                }else if (position == 4){   //我的
                    if (childPosition == 2){
                        swipeLayout.addDrag(SwipeLayout.DragEdge.Right, findViewById(R.id.side_right));
                        swipeLayout.setLeftSwipeEnabled(false);
                        swipeLayout.setRightSwipeEnabled(true);
                    }else {
                        swipeLayout.setLeftSwipeEnabled(false);
                        swipeLayout.setRightSwipeEnabled(false);
                    }
                }else {
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
    public void swipeStatus(SwipeBean swipeBean){
       childPosition = swipeBean.getPosition();
        MyLog.d("viewPager test : "+childPosition + "   "  +viewPager);
        MyLog.d("positionces : "+childPosition);
        if (viewPager.getCurrentItem() == 0){
            if (childPosition == 0) {
                swipeLayout.addDrag(SwipeLayout.DragEdge.Left, findViewById(R.id.random_shoot));
                swipeLayout.setLeftSwipeEnabled(true);
                swipeLayout.setRightSwipeEnabled(false);
            } else if (childPosition == 1) {
                swipeLayout.addDrag(SwipeLayout.DragEdge.Left, findViewById(R.id.random_shoot));
                swipeLayout.setLeftSwipeEnabled(false);
                swipeLayout.setRightSwipeEnabled(true);
            } else {
                swipeLayout.setLeftSwipeEnabled(false);
                swipeLayout.setRightSwipeEnabled(false);
            }
        }else if (viewPager.getCurrentItem() == 4){
            if (childPosition == 2 || swipeBean.isOpen()){
                swipeLayout.addDrag(SwipeLayout.DragEdge.Right, findViewById(R.id.side_right));
                swipeLayout.setLeftSwipeEnabled(false);
                swipeLayout.setRightSwipeEnabled(true);
                if (swipeBean.isOpen()){
                    if (swipeLayout.getOpenStatus() == SwipeLayout.Status.Open){
                        swipeLayout.close(true);
                    }else {
                        swipeLayout.open(true, swipeBean.getDragEdge());
                    }
                }
            }else {
                swipeLayout.setLeftSwipeEnabled(false);
                swipeLayout.setRightSwipeEnabled(false);
            }
        }
    }
    @OnClick(R.id.ivBottom)
    public void onViewClicked() {
//       startActivity(new Intent(MainActivity.this, Main3Activity.class));
//       startActivity(new Intent(MainActivity.this, EmojiActivity.class));
//       startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }

    @Override
    public void onBackPressed() {
        if (swipeLayout.getOpenStatus() == SwipeLayout.Status.Open){
            swipeLayout.close();
            return;
        }
        super.onBackPressed();
    }
}
