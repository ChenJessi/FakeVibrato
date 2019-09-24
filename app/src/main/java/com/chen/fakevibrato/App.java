package com.chen.fakevibrato;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chen.fakevibrato.im.IMManager;
import com.chen.fakevibrato.tangram.CustomHolderCell;
import com.chen.fakevibrato.tangram.CustomViewHolder;
import com.chen.fakevibrato.widget.glide.GlideApp;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.structure.viewcreator.ViewHolderCreator;
import com.tmall.wireless.tangram.util.IInnerImageSetter;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * @author Created by CHEN on 2019/6/10
 * @email 188669@163.com
 */
public class App extends Application {
    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // 初始化融云IM SDK，初始化 SDK 仅需要在主进程中初始化一次
        IMManager.getInstance().init(this);

    }



}
