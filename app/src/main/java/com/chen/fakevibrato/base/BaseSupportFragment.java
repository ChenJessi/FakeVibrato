package com.chen.fakevibrato.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.chen.baselibrary.base.BaseFragment;
import com.chen.baselibrary.base.BasePresenter;
import com.chen.baselibrary.base.BaseView;
import com.chen.fakevibrato.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Created by CHEN on 2019/6/11
 * @email 188669@163.cominitView
 */
public abstract class BaseSupportFragment<P extends BasePresenter> extends BaseFragment<P> implements BaseView {


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
        EventBus.getDefault().register(this);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(int event) {
        if (event == Constants.QUIT_ACTION) {

        }
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