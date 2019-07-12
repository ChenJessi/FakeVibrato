package com.chen.fakevibrato.listener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/**
 * @author Created by CHEN on 2019/7/12
 * @email 188669@163.com
 */
public class KeyboardOnGlobalChangeListener  implements ViewTreeObserver.OnGlobalLayoutListener {

    private Context context;
    int mScreenHeight = 0;
    Rect mRect = new Rect();
    private boolean mIsKeyboardActive;
    public KeyboardOnGlobalChangeListener(Context context) {
        this.context = context;
    }

    private int getScreenHeight() {
        if (mScreenHeight > 0) {
            return mScreenHeight;
        }
        mScreenHeight = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getHeight();
        return mScreenHeight;
    }

    @Override
    public void onGlobalLayout() {
        // 获取当前页面窗口的显示范围

        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(mRect);
        int screenHeight = getScreenHeight();
        int keyboardHeight = screenHeight - mRect.bottom; // 输入法的高度
        boolean isActive = false;
        if (Math.abs(keyboardHeight) > screenHeight / 5) {
            isActive = true; // 超过屏幕五分之一则表示弹出了输入法
        }
        mIsKeyboardActive = isActive;
//        onKeyboardStateChanged(mIsKeyboardActive, keyboardHeight);
    }



}
