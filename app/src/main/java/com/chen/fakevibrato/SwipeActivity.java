package com.chen.fakevibrato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.ViewDragHelper;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.annotationutils.CheckNet;
import com.chen.fakevibrato.bean.UserInfo;
import com.chen.fakevibrato.db.DaoSupport;
import com.chen.fakevibrato.db.DaoSupportFactory;
import com.chen.fakevibrato.db.IDaoSoupport;
import com.chen.fakevibrato.http.HttpCallBack;
import com.chen.fakevibrato.http.HttpUtils;
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.utils.MyLog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.jetbrains.annotations.NotNull;
import org.mp4parser.aspectj.lang.annotation.Before;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends AppCompatActivity {

    TextView text1;
    TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
                if (text1.getVisibility() == View.GONE){
                    text1.setVisibility(View.VISIBLE);
                }else {
                    text1.setVisibility(View.GONE);
                }

            }
        });

    }
    @CheckNet
    private void test(){
//      MyLog.e("点击执行了==========");
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
        HttpUtils.Companion.with(SwipeActivity.this)
                .url("www.baidu.com")
                .cache(true)
                .addParam("","")
                .execute(new HttpCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onError(@NotNull Exception e) {
                        super.onError(e);
                    }
                });
    }
}
