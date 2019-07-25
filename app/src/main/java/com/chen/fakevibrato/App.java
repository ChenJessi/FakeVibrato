package com.chen.fakevibrato;

import android.app.Application;
import android.content.Intent;

import com.chen.fakevibrato.im.IMManager;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * @author Created by CHEN on 2019/6/10
 * @email 188669@163.com
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化融云IM SDK，初始化 SDK 仅需要在主进程中初始化一次
        IMManager.getInstance().init(this);

    }

}
