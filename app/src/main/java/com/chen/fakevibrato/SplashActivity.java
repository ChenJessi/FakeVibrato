package com.chen.fakevibrato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;

import com.chen.fakevibrato.base.BaseActivity;
import com.chen.fakevibrato.im.IMManager;
import com.chen.fakevibrato.im.UserCache;
import com.chen.fakevibrato.ui.home.contract.SplashContract;
import com.chen.fakevibrato.ui.home.presenter.MainPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.rong.imlib.RongIMClient;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

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
        IMManager.getInstance().getAutoLoginResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {      //连接成功
                    toStart(MainActivity.class);
                } else {
//                    toStart(LoginActivity.class);
                    toStart(MainActivity.class);

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
