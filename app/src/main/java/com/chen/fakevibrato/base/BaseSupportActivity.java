package com.chen.fakevibrato.base;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.LayoutInflaterFactory;
import androidx.core.view.ViewCompat;

import com.chen.baselibrary.base.BaseActivity;
import com.chen.baselibrary.base.BasePresenter;
import com.chen.baselibrary.base.BaseView;
import com.chen.fakevibrato.skin.SkinManager;
import com.chen.fakevibrato.skin.attr.SkinAttr;
import com.chen.fakevibrato.skin.attr.SkinView;
import com.chen.fakevibrato.skin.support.SkinAppCompatViewInflater;
import com.chen.fakevibrato.skin.support.SkinAttrSupport;
import com.chen.fakevibrato.utils.Constants;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author Created by CHEN on 2019/6/10
 * @email 188669@163.com
 */
public abstract class BaseSupportActivity<P extends BasePresenter> extends BaseActivity<P> implements BaseView, LayoutInflaterFactory {
    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;

    private SkinAppCompatViewInflater mAppCompatViewInflater;
//    private SkinAppCompatViewInflater mAppCompatViewInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory(layoutInflater, this);

        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        EventBus.getDefault().register(this);
        mUnbinder = ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(int event) {
        if (event == Constants.QUIT_ACTION) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
        this.mUnbinder = null;
    }


    protected void initToolbar(Toolbar toolbar) {
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.height = QMUIDisplayHelper.getActionBarHeight(this) + QMUIDisplayHelper.getStatusBarHeight(this);
        toolbar.setLayoutParams(lp);
        toolbar.setPadding(0, QMUIDisplayHelper.getStatusBarHeight(this), 0, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        //拦截到view的创建 获取view之后解析
        //1. 创建view
        View view = createView(parent, name, context, attrs);

        // 2. 解析属性 src textcolor background

        //2.1 一个activity布局有多个 skinView
        if (view != null){
            List<SkinAttr> skinAttrs = SkinAttrSupport.INSTANCE.getSkinAttrs(context, attrs);
            SkinView skinView = new SkinView(view,skinAttrs);
            // 3.统一交给SkinManager管理
            managerSkinView(skinView);
        }
//        return super.onCreateView(parent, name, context, attrs);
        return view;
    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }

    /**
     * 统一管理skinView
     * @param skinView
     */
    private void managerSkinView(SkinView skinView){
        List<SkinView> skinViews = SkinManager.Companion.getInstance().getSkinViews(this);
        if (skinViews == null){
            skinViews = new ArrayList<>();
            SkinManager.Companion.getInstance().register(this, skinViews);
        }
        skinViews.add(skinView);
    }

    public View createView(View parent, final String name, @NonNull Context context,
                           @NonNull AttributeSet attrs) {
        final boolean isPre21 = Build.VERSION.SDK_INT < 21;

        if (mAppCompatViewInflater == null) {
            mAppCompatViewInflater = new SkinAppCompatViewInflater();
//            mAppCompatViewInflater = new com.chen.fakevibrato.skin.SkinAppCompatViewInflater();
        }

        // We only want the View to inherit it's context if we're running pre-v21
        final boolean inheritContext = isPre21 && true
                && shouldInheritContext((ViewParent) parent);

        return mAppCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                isPre21, /* Only read android:theme pre-L (L+ handles this anyway) */
                true /* Read read app:theme as a fallback at all times for legacy reasons */
        );
    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == getWindow().getDecorView() || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }
}