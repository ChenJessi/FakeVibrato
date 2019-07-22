package com.chen.fakevibrato.ui.my.view;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseFragment;
import com.chen.fakevibrato.ui.my.presenter.UserVideoPresenter;

import butterknife.BindView;

/**
 * @author Created by CHEN on 2019/7/22
 * @email 188669@163.com
 */
public class UserVideoListFragment extends BaseFragment<UserVideoPresenter> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int setView() {
        return R.layout.fragment_user_video;
    }

    @Override
    protected UserVideoPresenter initPresenter() {
        return new UserVideoPresenter();
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
