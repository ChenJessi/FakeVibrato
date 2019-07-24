package com.chen.fakevibrato.ui.message.view;

import android.os.Bundle;
import android.view.View;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseFragment;
import com.chen.fakevibrato.ui.message.presenter.MessagePresenter;

/**
 * @author Created by CHEN on 2019/7/23
 * @email 188669@163.com
 * 消息
 */
public class MessageFragment extends BaseFragment<MessagePresenter> {
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

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void Load() {

    }
}
