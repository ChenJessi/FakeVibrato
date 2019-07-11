package com.chen.fakevibrato;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chen.fakevibrato.base.BaseActivity;
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.ui.home.contract.MainContract;
import com.chen.fakevibrato.ui.home.presenter.MainPresenter;
import com.chen.fakevibrato.ui.home.view.HomeFragment;
import com.chen.fakevibrato.utils.DisplayUtils;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.CommentSpan;
import com.daimajia.swipe.SwipeLayout;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qmuiteam.qmui.span.QMUITouchableSpan;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private MyPagerAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    String[] mTitles = new String[]{"首页", "关注", "", "消息", "我"};

    @Override
    protected void initView() {

        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
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
        swipeLayout.setRightSwipeEnabled(true);
        swipeLayout.setLeftSwipeEnabled(true);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, findViewById(R.id.random_shoot));
        swipeLayout.addDrag(SwipeLayout.DragEdge.Right, findViewById(R.id.random_shoot));



            String s = "https://wenku.baidu.com/view/e109601f52d380eb62946d75.html?rec_flag=default&mark_pay_doc=2&mark_rec_page=1&mark_rec_position=4&mark_rec=view_r_1&clear_uda_param=1\n" ;
            List<String> strs = new ArrayList<String>();
            String aaa = "#测试@朋友 $#话题@a#s ";
//        CommentSpan sb = new CommentSpan(aaa);

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
                viewPager.setCurrentItem(position);
                TextView textView = mTabLayout.getTitleView(position);
                String text = textView.getText().toString().trim();
                TextPaint textPaint = textView.getPaint();
                int textPaintWidth = (int) textPaint.measureText(text);
                mTabLayout.setIndicatorWidth(DisplayUtils.px2dp(MainActivity.this, textPaintWidth));
                if (position == 0 || position == 1){
                    swipeLayout.setLeftSwipeEnabled(true);
                    swipeLayout.setRightSwipeEnabled(true);
                    swipeLayout.addDrag(SwipeLayout.DragEdge.Left, findViewById(R.id.random_shoot));
                    swipeLayout.addDrag(SwipeLayout.DragEdge.Right, findViewById(R.id.random_shoot));
                }else if (position == 4){
                    swipeLayout.setLeftSwipeEnabled(false);
                    swipeLayout.setRightSwipeEnabled(true);
                    swipeLayout.addDrag(SwipeLayout.DragEdge.Right, findViewById(R.id.side_right));
                }else if (position == 3){
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

    @OnClick(R.id.ivBottom)
    public void onViewClicked() {
       startActivity(new Intent(MainActivity.this, Main3Activity.class));
//       startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }

}
