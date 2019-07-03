package com.chen.fakevibrato.widget;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

import com.chen.fakevibrato.utils.MyLog;
import com.qmuiteam.qmui.link.ITouchableSpan;

/**
 * @author Created by CHEN on 2019/7/3
 * @email 188669@163.com
 */
public abstract class TouchableSpan extends ClickableSpan implements ITouchableSpan {

    private boolean mIsPressed;
    @ColorInt
    private int mNormalBackgroundColor;
    @ColorInt private int mPressedBackgroundColor;
    @ColorInt private int mNormalTextColor;
    @ColorInt private int mPressedTextColor;

    private boolean mIsNeedUnderline = false;

    public abstract void onSpanClick(View widget);

    @Override
    public  void onClick(View widget) {
        MyLog.d("点击了");
        if (ViewCompat.isAttachedToWindow(widget)) {
            onSpanClick(widget);
        }
    }

    public TouchableSpan(@ColorInt int normalTextColor,
                         @ColorInt int pressedTextColor,
                         @ColorInt int normalBackgroundColor,
                         @ColorInt int pressedBackgroundColor) {
        mNormalTextColor = normalTextColor;
        mPressedTextColor = pressedTextColor;
        mNormalBackgroundColor = normalBackgroundColor;
        mPressedBackgroundColor = pressedBackgroundColor;
    }

    public int getNormalBackgroundColor() {
        return mNormalBackgroundColor;
    }

    public void setNormalTextColor(int normalTextColor) {
        mNormalTextColor = normalTextColor;
    }

    public void setPressedTextColor(int pressedTextColor) {
        mPressedTextColor = pressedTextColor;
    }

    public int getNormalTextColor() {
        return mNormalTextColor;
    }

    public int getPressedBackgroundColor() {
        return mPressedBackgroundColor;
    }

    public int getPressedTextColor() {
        return mPressedTextColor;
    }

    public void setPressed(boolean isSelected) {
        mIsPressed = isSelected;
    }

    public boolean isPressed() {
        return mIsPressed;
    }

    public void setIsNeedUnderline(boolean isNeedUnderline) {
        mIsNeedUnderline = isNeedUnderline;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(mIsPressed ? mPressedTextColor : mNormalTextColor);
        ds.bgColor = mIsPressed ? mPressedBackgroundColor
                : mNormalBackgroundColor;
        ds.setUnderlineText(mIsNeedUnderline);
    }
}
