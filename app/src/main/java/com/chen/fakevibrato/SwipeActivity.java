package com.chen.fakevibrato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;
import com.chen.fakevibrato.utils.MyLog;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends AppCompatActivity {
    private MyPagerAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private QMUIViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        viewPager = findViewById(R.id.viewPager);
        mFragments.add(new SwipeFragment());
        mFragments.add(new SwipeFragment());
//        mFragments.add(new MainFragment());
        mFragments.add(new SwipeFragment());
        adapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(adapter);
        viewPager.setSwipeable(true);



//        textView = findViewById(R.id.textView);
//        button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (textView.getVisibility() == View.VISIBLE){
//                    textView.setVisibility(View.GONE);
//                }else {
//                    textView.setVisibility(View.VISIBLE);
//                }
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() ){
            case MotionEvent.ACTION_DOWN:
                MyLog.e("ACTION_DOWN   : "+event.getX());
                break;
            case MotionEvent.ACTION_MOVE:
                MyLog.e("ACTION_MOVE   : "+event.getX());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }


        return super.onTouchEvent(event);
    }
}
