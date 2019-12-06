package com.chen.fakevibrato;

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

import com.chen.annotationutils.CheckNet;
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.utils.MyLog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

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
                MyLog.e("点击执行了==========");
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
    public void test(){
      MyLog.e("点击执行了==========");
    }
}
