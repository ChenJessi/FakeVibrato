package com.chen.fakevibrato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chen.fakevibrato.base.BaseActivity;
import com.chen.fakevibrato.ui.home.presenter.MainPresenter;

public class SplashActivity extends BaseActivity<MainPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected MainPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
