package com.chen.fakevibrato.widget.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.customview.widget.ViewDragHelper;
import androidx.viewpager.widget.ViewPager;

public class MySwipeLayout extends LinearLayout {
    private ViewDragHelper mDragHelper;
    private GestureDetectorCompat mGestureDetectorCompat;
    private Scroller mScroller;

    public MySwipeLayout(Context context) {
        this(context, null);
    }

    public MySwipeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySwipeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mDragHelper = ViewDragHelper.create(this, mDragHelperCallback);
        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), new XScrollDetector());
        mScroller = new Scroller(getContext(), sInterpolator);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }


    //豎直方向上才滚动
    private class XScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return   Math.abs(distanceX) > Math.abs(distanceY);
        }
    }

    private static final Interpolator sInterpolator = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t * t * t + 1.0f;
        }
    };
}
