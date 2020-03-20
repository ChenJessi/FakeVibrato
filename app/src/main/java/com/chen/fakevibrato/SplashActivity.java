package com.chen.fakevibrato;

import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

import com.chen.baselibrary.fix.FixDexManager;
import com.chen.fakevibrato.base.BaseSupportActivity;
import com.chen.fakevibrato.im.IMManager;
import com.chen.fakevibrato.ui.home.presenter.MainPresenter;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.stack.TestSpringActivity;


import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseSupportActivity<MainPresenter> {
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

        IMManager.getInstance().getAutoLoginResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {      //连接成功
                    toStart(MainActivity.class);
                } else {
//                    toStart(LoginActivity.class);
//                    toStart(MainActivity.class);
                    toStart(MainHActivity.class);
//                    toStart(TestSpringActivity.class);

                }
            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 1 秒钟后跳转到首页 或者登陆
     *
     * @param cls
     */
    private void toStart(Class<?> cls) {

        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        startActivity(new Intent(SplashActivity.this, cls));
                        finish();
                    }
                });
    }
}
