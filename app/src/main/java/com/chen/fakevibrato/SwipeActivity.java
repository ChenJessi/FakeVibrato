package com.chen.fakevibrato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends AppCompatActivity {
    private MyPagerAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        mFragments.add(new SwipeFragment());
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
}
