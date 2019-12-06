package com.chen.baselibrary.base;


import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;


/**
 * @author Created by CHEN on 2019/6/10
 * @email 188669@163.com
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    protected final String TAG = this.getClass().getSimpleName();
    protected P mPresenter;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        if (getLayoutId()!=0){
            setContentView(getLayoutId());
        }
        mPresenter =  initPresenter();
        if (mPresenter != null){
            mPresenter.attachView(this);
        }
        mContext = this.getApplicationContext();

//        initView();
//        initListener();
//        initData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mPresenter = null;
    }


    protected abstract int getLayoutId();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

}