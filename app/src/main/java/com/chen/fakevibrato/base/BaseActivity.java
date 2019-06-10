package com.chen.fakevibrato.base;

/**
 * @author Created by CHEN on 2019/6/10
 * @email 188669@163.com
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.chen.fakevibrato.App;
import com.chen.fakevibrato.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected App mApplication;
    private Unbinder mUnbinder;
    //    private MyBaseActiviy_Broad oBaseActiviy_Broad;


    protected Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (App) getApplication();


        if (getLayoutId()!=0){
            setContentView(getLayoutId());
        }

        EventBus.getDefault().register(this);
        mUnbinder = ButterKnife.bind(this);
        mContext = this.getApplicationContext();
        //        oBaseActiviy_Broad = new MyBaseActiviy_Broad();
        //        IntentFilter intentFilter = new IntentFilter("drc.xxx.yyy.baseActivity");
        //        registerReceiver(oBaseActiviy_Broad, intentFilter);


        initView();
        initListener();
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(int event) {
        if (event == Constants.QUIT_ACTION){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
//        RxManager.get().cancel(this);
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();

        this.mUnbinder = null;
        this.mApplication = null;

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();


}
