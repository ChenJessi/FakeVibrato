package com.chen.fakevibrato;

import android.app.Application;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * @author Created by CHEN on 2019/6/10
 * @email 188669@163.com
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BGASwipeBackHelper.init(this, null);
    }
}
