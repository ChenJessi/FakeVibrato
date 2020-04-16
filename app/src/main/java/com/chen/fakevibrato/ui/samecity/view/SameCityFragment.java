package com.chen.fakevibrato.ui.samecity.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.DBaseSupportFragment;
import com.chen.fakevibrato.ui.samecity.adapter.SameAdapter;
import com.chen.fakevibrato.ui.samecity.adapter.SameTypeAdapter;
import com.chen.fakevibrato.ui.samecity.presenter.SameCityPresenter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by CHEN on 2019/7/23
 * @email 188669@163.com
 * 同城
 */
public class SameCityFragment extends DBaseSupportFragment<SameCityPresenter> {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.clLocation)
    ConstraintLayout clLocation;
    @BindView(R.id.rvType)
    RecyclerView rvType;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private SameTypeAdapter sameTypeAdapter;
    private SameAdapter adapter;

    public static SameCityFragment newInstance() {
        return new SameCityFragment();
    }

    @Override
    protected int setView() {
        return R.layout.fragment_same_city;
    }

    @Override
    protected SameCityPresenter initPresenter() {
        return new SameCityPresenter();
    }

    @Override
    protected void initView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.height = QMUIDisplayHelper.getActionBarHeight(getActivity()) + QMUIDisplayHelper.getStatusBarHeight(getActivity());
        toolbar.setLayoutParams(lp);
        toolbar.setPadding(0,QMUIDisplayHelper.getStatusBarHeight(getActivity()),0,0);

        sameTypeAdapter = new SameTypeAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvType.setLayoutManager(manager);
        rvType.setAdapter(sameTypeAdapter);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new SameAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void Load() {

    }

    @OnClick(R.id.clLocation)
    public void onViewClicked() {
    }
}
