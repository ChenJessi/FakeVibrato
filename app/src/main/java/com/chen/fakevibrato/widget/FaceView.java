package com.chen.fakevibrato.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.qmuiteam.qmui.qqface.IQMUIQQFaceManager;
import com.qmuiteam.qmui.qqface.QMUIQQFaceCompiler;
import com.qmuiteam.qmui.qqface.QMUIQQFaceView;
import com.qmuiteam.qmui.widget.textview.QMUILinkTextView;

/**
 * @author Created by CHEN on 2019/6/17
 * @email 188669@163.com
 */
public class FaceView extends QMUIQQFaceView {
    public FaceView(Context context) {
        super(context);
    }

    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setCompiler(QMUIQQFaceCompiler.getInstance(FaceManager.getInstance()));
    }
}
