package com.chen.fakevibrato.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chen.fakevibrato.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

/**
 * @author Created by CHEN on 2019/6/18
 * @email 188669@163.com
 */
public class CommentDialog extends BottomSheetDialog {
    private CommentDialog mDialog;
    public CommentDialog(@NonNull Context context) {
        super(context, R.style.BottomSheet);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public static class Builder{
        private Context context;
        public Builder(Context context) {
            this.context = context;
        }

        public void show(){

        }
        public void dismiss(){
        }

    }
}
