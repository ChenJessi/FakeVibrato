package com.chen.fakevibrato.widget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.Image;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.utils.MyLog;
import com.qmuiteam.qmui.util.QMUIKeyboardHelper;

/**
 * @author Created by CHEN on 2019/7/12
 * @email 188669@163.com
 */
public class EmojiDialog extends Dialog {


    public EmojiDialog(@NonNull Context context) {
        super(context,R.style.BottomSheet);
    }

    public static class Builder{
        private EmojiDialog mDialog;
        private Context context;
        private RecyclerView recyclerView;
        private EditText etContent;
        private ImageView ivAt;
        private boolean isEmoji;
        public Builder(Context context) {
            this.context = context;
        }
        private void create() {
            mDialog = new EmojiDialog(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_emoji, null);
            recyclerView = view.findViewById(R.id.recyclerView);
            etContent = view.findViewById(R.id.etContent);
            ivAt = view.findViewById(R.id.ivAt);
            mDialog.setContentView(view);
            Window window = mDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            recyclerView.setVisibility(View.GONE);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

            ivAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerView.setVisibility(View.VISIBLE);
                    QMUIKeyboardHelper.hideKeyboard(etContent);

//                    if (QMUIKeyboardHelper.isKeyboardVisible((Activity) context)) { // 输入法打开状态下
//                        if (!isEmoji) { // 打开表情
////                            mSwitchBtn.setBackgroundResource(R.drawable.icon_key);
//                            // 设置为不会调整大小，以便输入弹起时布局不会改变。若不设置此属性，输入法弹起时布局会闪一下
//                            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
//                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(etContent.getApplicationWindowToken(), 0);
//                            recyclerView.setVisibility(View.VISIBLE);
//                            isEmoji = true;
//                        } else {
////                            mSwitchBtn.setBackgroundResource(R.drawable.icon_more);
//                            recyclerView.setVisibility(View.GONE);
//                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(etContent.getApplicationWindowToken(), 0);
//                            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//                                    | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//                            isEmoji = false;
//                        }
//                    } else {
//                        //  输入法关闭状态下
//                        if (!isEmoji) {
////                            mSwitchBtn.setBackgroundResource(R.drawable.icon_key);
//                            // 设置为不会调整大小，以便输入弹起时布局不会改变。若不设置此属性，输入法弹起时布局会闪一下
//                            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
//                            recyclerView.setVisibility(View.VISIBLE);
//                            isEmoji = true;
//                        } else {
////                            mSwitchBtn.setBackgroundResource(R.drawable.icon_more);
//                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
//                            isEmoji = false;
//                            ivAt.postDelayed(new Runnable() {
//                                @Override
//                                public void run() { // 输入法弹出之后，重新调整
//                                    recyclerView.setVisibility(View.GONE);
//                                    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//                                            | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//                                }
//                            }, 250); // 延迟一段时间，等待输入法完全弹出*/
//                        }
//                    }
                }
            });
            etContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    etContent.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            recyclerView.setVisibility(View.GONE);
//                            // 输入法弹出之后，重新调整
//                            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//                                    | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//                        }
//                    }, 250); // 延迟一段时间，
                }
            });
            mDialog.setOnShowListener(new OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    QMUIKeyboardHelper.showKeyboard(etContent,100);
                }
            });
            QMUIKeyboardHelper.setVisibilityEventListener((Activity) context, new QMUIKeyboardHelper.KeyboardVisibilityEventListener() {
                @Override
                public boolean onVisibilityChanged(boolean isOpen, int heightDiff) {
                    MyLog.d("heightDiff : "+ heightDiff +"  isOpen : "+isOpen);
                    if (isOpen){
//                        recyclerView.setVisibility(View.GONE);
//                        recyclerView.setVisibility(View.VISIBLE);

                    }else {

//                        recyclerView.setVisibility(View.GONE);
//                        recyclerView.setVisibility(View.VISIBLE);
                    }

                    return false;
                }
            });
        }

        public EmojiDialog show(){
            create();
            mDialog.show();
            return mDialog;
        }
        public void dismiss(){
            mDialog.dismiss();
        }
    }



}
