package com.chen.fakevibrato.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.utils.MyLog;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

/**
 * 仿抖音点击红心
 * @author Created by CHEN on 2019/6/15
 * @email 188669@163.com
 */
public class HeartLayout extends ConstraintLayout {
    private Context mContext;
    private boolean isDouble = false;
    private static long lastClickTime = 0;
    private float[] num = {-30, -20, 0, 20, 30};//随机心形图片角度
    private OnLikeListener onLikeListener;
    public HeartLayout(Context context) {
        super(context);
        initView(context);
    }
    public HeartLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    public HeartLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context) {
        mContext = context;
    }
    public void setOnLikeListener(OnLikeListener onLikeListener){
        this.onLikeListener = onLikeListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isDouble){
            final ImageView imageView = new ImageView(mContext);
            LayoutParams params = new LayoutParams(300, 300);
            params.leftMargin = (int) event.getX() - 150;
            params.topMargin = (int) event.getY() - 300;
            params.topToTop = 0;
            params.leftToLeft = 0;
            imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.heart_red));
            imageView.setLayoutParams(params);
            addView(imageView);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(scale(imageView, "scaleX", 2f, 0.9f, 100, 0))
                    .with(scale(imageView, "scaleY", 2f, 0.9f, 100, 0))
                    .with(rotation(imageView, 0, 0, num[new Random().nextInt(4)]))
                    .with(alpha(imageView, 0, 1, 100, 0))
                    .with(scale(imageView, "scaleX", 0.9f, 1, 50, 0))
                    .with(scale(imageView, "scaleY", 0.9f, 1, 50, 0))
                    .with(translationY(imageView, 0, -600, 800, 400))
                    .with(alpha(imageView, 1, 0, 300, 400))
                    .with(scale(imageView, "scaleX", 1, 3f, 700, 200))
                    .with(scale(imageView, "scaleY", 1, 3f, 700, 200));
            animatorSet.start();
            if (onLikeListener != null){
                onLikeListener.onClick();
            }
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    removeViewInLayout(imageView);
                }
            });
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastClickTime < 300) {
            isDouble = true;
            lastClickTime = currentTimeMillis;
            return false;
        }else {
            isDouble = false;
            lastClickTime = currentTimeMillis;
            return super.onInterceptTouchEvent(ev);
        }

    }

    private   ObjectAnimator scale(View view, String propertyName, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , propertyName
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }
    private   ObjectAnimator translationX(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "translationX"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }
    private   ObjectAnimator translationY(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "translationY"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }
    private   ObjectAnimator alpha(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "alpha"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }
    private   ObjectAnimator rotation(View view, long time, long delayTime, float... values) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", values);
        rotation.setDuration(time);
        rotation.setStartDelay(delayTime);
        rotation.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        return rotation;
    }

    public interface OnLikeListener{
        void onClick();
    }
}
