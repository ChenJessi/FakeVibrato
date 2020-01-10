package com.chen.fakevibrato;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chen.baselibrary.BaseApplication;
import com.chen.baselibrary.fix.ExceptionCrashHandler;
import com.chen.baselibrary.fix.FixDexManager;
import com.chen.fakevibrato.http.HttpUtils;
import com.chen.fakevibrato.im.IMManager;
import com.chen.fakevibrato.skin.SkinManager;


import java.io.File;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * @author Created by CHEN on 2019/6/10
 * @email 188669@163.com
 */
public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ExceptionCrashHandler.Companion.getInstance().init(this);
        SkinManager.Companion.getInstance().init(this);
        try {
            FixDexManager fixDexManager = new FixDexManager(this);
            fixDexManager.loadFixDex();
        } catch (Exception e) {
            e.printStackTrace();
        }
        IMManager.getInstance().init(this);

    }


}
