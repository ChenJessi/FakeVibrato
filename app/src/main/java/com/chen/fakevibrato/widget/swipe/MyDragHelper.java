package com.chen.fakevibrato.widget.swipe;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;

public class MyDragHelper extends ViewDragHelper.Callback {
    @Override
    public boolean tryCaptureView(@NonNull View child, int pointerId) {
        return true;
    }

    @Override
    public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
        return super.clampViewPositionHorizontal(child, left, dx);
    }
}
