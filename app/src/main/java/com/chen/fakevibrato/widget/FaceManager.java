package com.chen.fakevibrato.widget;

import android.graphics.drawable.Drawable;

import com.qmuiteam.qmui.qqface.IQMUIQQFaceManager;

/**
 * @author Created by CHEN on 2019/6/17
 * @email 188669@163.com
 */
public class FaceManager implements IQMUIQQFaceManager {



    @Override
    public boolean maybeSoftBankEmoji(char c) {
        return false;
    }

    @Override
    public int getSoftbankEmojiResource(char c) {
        return 0;
    }

    @Override
    public boolean maybeEmoji(int codePoint) {
        return false;
    }

    @Override
    public int getEmojiResource(int codePoint) {
        return 0;
    }

    @Override
    public int getDoubleUnicodeEmoji(int currentCodePoint, int nextCodePoint) {
        return 0;
    }

    @Override
    public int getQQfaceResource(CharSequence text) {
        return 0;
    }

    @Override
    public Drawable getSpecialBoundsDrawable(CharSequence text) {
        return null;
    }

    @Override
    public int getSpecialDrawableMaxHeight() {
        return 0;
    }
}
