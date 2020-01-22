package com.chen.fakevibrato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.ViewDragHelper;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.annotationutils.CheckNet;
import com.chen.baselibrary.base.BasePresenter;
import com.chen.fakevibrato.base.BaseSupportActivity;
import com.chen.fakevibrato.bean.UserInfo;
import com.chen.fakevibrato.db.DaoSupport;
import com.chen.fakevibrato.db.DaoSupportFactory;
import com.chen.fakevibrato.db.IDaoSoupport;
import com.chen.fakevibrato.http.HttpCallBack;
import com.chen.fakevibrato.http.HttpUtils;
import com.chen.fakevibrato.skin.SkinManager;
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.utils.MyLog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.jetbrains.annotations.NotNull;
import org.mp4parser.aspectj.lang.annotation.Before;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends BaseSupportActivity {
    private TextView text1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_swipe;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        text1 = findViewById(R.id.text1);
    }

    @Override
    protected void initListener() {
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    @Override
    protected void initData() {

    }

//    @CheckNet
    private void test(){
//        val fixFile = new File();
        File file = new File("/mnt/sdcard/tencent/QQfile_recv/test.skin");
        MyLog.e("点击执行了=========="+file);
        SkinManager.Companion.getInstance().loadSkin(file.getPath());

//        IDaoSoupport<UserInfo> daoSoupport = DaoSupportFactory.Companion.getFactory().getDao(UserInfo.class);
//        List<UserInfo> userInfos = new ArrayList<>();
//
//        for (int i = 0; i < 5000; i++) {
//            UserInfo userInfo = new UserInfo();
//            userInfo.setName("测试  :  ");
//            userInfos.add(userInfo);
//        }
//        daoSoupport.insert(userInfos);
//
//        List<UserInfo> users = daoSoupport.query();
//        Toast.makeText(this, "users -> " + users.size(), Toast.LENGTH_LONG).show();
//        HttpUtils.Companion.with(SwipeActivity.this)
//                .url("www.baidu.com")
//                .cache(true)
//                .addParam("","")
//                .execute(new HttpCallBack<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//
//                    }
//
//                    @Override
//                    public void onError(@NotNull Exception e) {
//                        super.onError(e);
//                    }
//                });
    }
}
