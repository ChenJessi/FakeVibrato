package com.chen.fakevibrato.ui.home.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseFragment;
import com.chen.fakevibrato.ui.home.adapter.HomeAdapter;
import com.chen.fakevibrato.ui.home.contract.HomeContract;
import com.chen.fakevibrato.ui.home.presenter.HomePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Created by CHEN on 2019/6/11
 * @email 188669@163.com
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private HomeAdapter adapter;
    private List<String> mList = new ArrayList<>();
    private PagerSnapHelper helper;
    @Override
    protected int setView() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView(View view) {

        adapter = new HomeAdapter(getActivity(),mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        mList.add("测试测试");
        mList.add("测试测试11");
        mList.add("测试测试2222");
        adapter.notifyItemRangeChanged(mList.size() -3 , 3);
    }

    int i = 0;
    @Override
    protected void Load() {
        Log.e("test", "load : "+ i + " : "+isFirstVisible());
        i++;
        mList.add("aaaa");
        mList.add("bbbb");
        mList.add("cccc");
        adapter.notifyItemRangeChanged(mList.size() -3 , 3);
    }

}
