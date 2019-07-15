package com.chen.fakevibrato.ui.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseFragment;
import com.chen.fakevibrato.ui.home.adapter.HomeAdapter;
import com.chen.fakevibrato.ui.home.contract.HomeContract;
import com.chen.fakevibrato.ui.home.presenter.HomePresenter;
import com.chen.fakevibrato.widget.CommentDialog;
import com.chen.fakevibrato.widget.EmojiActivity;
import com.chen.fakevibrato.widget.EmojiDialog;
import com.chen.fakevibrato.widget.LoadingView;
import com.qmuiteam.qmui.qqface.QMUIQQFaceCompiler;
import com.qmuiteam.qmui.qqface.QMUIQQFaceView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by CHEN on 2019/6/11
 * @email 188669@163.com
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tvRandom)
    TextView tvRandom;
    @BindView(R.id.tvRecommend)
    TextView tvRecommend;
    @BindView(R.id.tvLocal)
    TextView tvLocal;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.ivLive)
    ImageView ivLive;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

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

        adapter = new HomeAdapter(getActivity(), mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(recyclerView);

        initListener();
    }

    LoadingView loadingView;

    @Override
    protected void initData(Bundle savedInstanceState) {

        // refreshLayout.setRefreshContent(recyclerView);

//        loadingView.stop();
//        refreshLayout.finishRefresh();//结束刷新
//        refreshLayout.setRefreshContent(recyclerView);
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        loadingView = new LoadingView(getActivity());
        loadingView.start();
        if (refreshLayout != null){
            refreshLayout.setRefreshContent(loadingView);
        }
    }

    int i = 0;

    @Override
    protected void Load() {
        if (refreshLayout != null) {
            loadingView.stop();
            refreshLayout.finishRefresh();//结束刷新
            refreshLayout.setRefreshContent(recyclerView);
        }

        Log.e("test", "load : " + i + " : " + isFirstVisible());
        i++;
        mList.add("aaaa");
        mList.add("bbbb");
        mList.add("cccc");
        adapter.notifyItemRangeChanged(mList.size() - 3, 3);
    }

    @OnClick({R.id.tvRandom, R.id.tvRecommend, R.id.tvLocal, R.id.ivSearch, R.id.ivLive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvRandom:
                break;
            case R.id.tvRecommend:
                break;
            case R.id.tvLocal:
                break;
            case R.id.ivSearch:
                break;
            case R.id.ivLive:
                break;
        }
    }

    private void initListener(){
        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onLikes(int position) {

            }

            @Override
            public void onComment(int position) {
                new CommentDialog.Builder(getActivity()).setOnDialogListener(new CommentDialog.Builder.OnDialogListener() {
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
                }).show();
            }

            @Override
            public void onReprinted(int position) {

            }
        });
    }
}
