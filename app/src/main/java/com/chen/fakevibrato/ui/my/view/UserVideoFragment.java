package com.chen.fakevibrato.ui.my.view;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.DBaseSupportFragment;
import com.chen.fakevibrato.ui.my.adapter.UserVideoAdapter;
import com.chen.fakevibrato.ui.my.presenter.UserVideoPresenter;

import butterknife.BindView;

/**
 * @author Created by CHEN on 2019/7/22
 * @email 188669@163.com
 */
public class UserVideoFragment extends DBaseSupportFragment<UserVideoPresenter> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private UserVideoAdapter adapter;
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
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        adapter = new UserVideoAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void Load() {

    }
}
