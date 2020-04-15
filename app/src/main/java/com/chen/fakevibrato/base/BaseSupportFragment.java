package com.chen.fakevibrato.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.chen.baselibrary.base.BaseLazyFragment;
import com.chen.baselibrary.base.BasePresenter;
import com.chen.baselibrary.base.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Created by CHEN on 2019/6/11
 * @email 188669@163.cominitView
 */
public abstract class BaseSupportFragment<P extends BasePresenter> extends BaseLazyFragment<P> implements BaseView {


    private Unbinder mUnbinder;
    /**
     * 是否显示
     */
    private boolean isFragmentVisible;
    /**
     * 是否是第一次显示
     */
    private boolean isFirstVisible;
    /**
     * 设置是否使用 view 的复用，默认开启
     */
    private boolean isReuseView = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
//        EventBus.getDefault().register(this);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
    }

    private void initVariable() {
        this.mUnbinder = null;
    }

}