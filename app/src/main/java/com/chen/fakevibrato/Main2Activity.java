package com.chen.fakevibrato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView image = findViewById(R.id.image);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.attention);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.attention2);
        //启动动画
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.startAnimation(animation);
            }
        });

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.setImageResource(R.mipmap.hook_red);
//                image.setImageDrawable(ContextCompat.getDrawable(Main2Activity.this, R.mipmap.add_red));
                image.startAnimation(animation1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
