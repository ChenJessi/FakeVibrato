package com.chen.fakevibrato.ui.message.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseFragment;
import com.chen.fakevibrato.ui.message.presenter.MessagePresenter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import butterknife.BindView;

/**
 * @author Created by CHEN on 2019/7/23
 * @email 188669@163.com
 * 消息
 */
public class MessageFragment extends BaseFragment<MessagePresenter> {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected int setView() {
        return R.layout.fragment_message;
    }

    @Override
    protected MessagePresenter initPresenter() {
        return new MessagePresenter();
    }

    @Override
    protected void initView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.height = QMUIDisplayHelper.getActionBarHeight(getActivity()) + QMUIDisplayHelper.getStatusBarHeight(getActivity());
        toolbar.setLayoutParams(lp);
        toolbar.setPadding(0,QMUIDisplayHelper.getStatusBarHeight(getActivity()),0,0);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void Load() {

    }
}
