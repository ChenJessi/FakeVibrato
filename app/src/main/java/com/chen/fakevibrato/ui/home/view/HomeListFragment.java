package com.chen.fakevibrato.ui.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseFragment;
import com.chen.fakevibrato.ui.home.adapter.HomeListAdapter;
import com.chen.fakevibrato.ui.home.contract.HomeListContract;
import com.chen.fakevibrato.ui.home.presenter.HomeListPresenter;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.CommentDialog;
import com.chen.fakevibrato.widget.LoadingView;
import com.chen.fakevibrato.widget.emojipanel.EmojiActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 首页列表fragment
 * @author Created by CHEN on 2019/7/18
 * @email 188669@163.com
 */
public class HomeListFragment extends BaseFragment<HomeListPresenter> implements HomeListContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private HomeListAdapter adapter;
    private List<String> mList = new ArrayList<>();
    private PagerSnapHelper helper;

    private CommentDialog.Builder builder;
    LoadingView loadingView;
    @Override
    protected int setView() {
        return R.layout.fragment_home_list;
    }

    @Override
    protected HomeListPresenter initPresenter() {
        return new HomeListPresenter();
    }

    @Override
    protected void initView(View view) {
        adapter = new HomeListAdapter(getActivity(), mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(recyclerView);

        initListener();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        loadingView = new LoadingView(getActivity());
        loadingView.start();
        if (refreshLayout != null) {
            refreshLayout.setRefreshContent(loadingView);
        }
    }

    @Override
    protected void Load() {
        if (refreshLayout != null) {
            loadingView.stop();
            refreshLayout.finishRefresh();//结束刷新
            refreshLayout.setRefreshContent(recyclerView);
        }

        mList.add("chen");
        mList.add("chen");
        mList.add("chen");
        adapter.notifyItemRangeChanged(mList.size() - 3, 3);
    }

    private void initListener(){
        adapter.setOnItemClickListener(new HomeListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onLikes(int position) {

            }

            @Override
            public void onComment(int position) {
                MyLog.d("builder : "+builder);
                if (builder != null){
                    builder.show();
                }else {
                    builder = new CommentDialog.Builder(getActivity()).setOnDialogListener(new CommentDialog.Builder.OnDialogListener() {
                        @Override
                        public void emojiClick() {
                            startActivity(new Intent(getActivity(), EmojiActivity.class));
                        }
                        @Override
                        public void atClick() {
                        }
                        @Override
                        public void commentClick() {
                            startActivity(new Intent(getActivity(), EmojiActivity.class));
                        }
                    });
                    builder.show();
                }
            }

            @Override
            public void onReprinted(int position) {

            }
        });
    }
}
