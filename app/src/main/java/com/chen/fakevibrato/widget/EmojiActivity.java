//package com.chen.fakevibrato.widget;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Rect;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewTreeObserver;
//import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import com.chen.fakevibrato.R;
//import com.chen.fakevibrato.listener.KeyboardOnGlobalChangeListener;
//import com.qmuiteam.qmui.util.QMUIKeyboardHelper;
//
//public class EmojiActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private EditText editText;
//    private ImageView imageView;
//    private boolean isEmoji = false;
//    private int keyboardHeight = 0;
//    private boolean mIsKeyboardActive;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_emoji);
//        recyclerView = findViewById(R.id.recyclerView);
//        imageView = findViewById(R.id.imageView);
//        editText = findViewById(R.id.editText);
//
//        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new KeyboardOnGlobalChangeListener());
//
//
//
////            if(keyboardHeight>0) {
////                ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
////                params.height = keyboardHeight;
////                recyclerView.setLayoutParams(params);
////            }
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                recyclerView.setVisibility(View.VISIBLE);
//                QMUIKeyboardHelper.hideKeyboard(editText);
//
//
//                if (QMUIKeyboardHelper.isKeyboardVisible(EmojiActivity.this)) { // 输入法打开状态下
//                    if (!isEmoji) { // 打开表情
//                        //                            mSwitchBtn.setBackgroundResource(R.drawable.icon_key);
//                        // 设置为不会调整大小，以便输入弹起时布局不会改变。若不设置此属性，输入法弹起时布局会闪一下
//                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
//                        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
//                        recyclerView.setVisibility(View.VISIBLE);
//                        isEmoji = true;
//                    } else {
//                        //                            mSwitchBtn.setBackgroundResource(R.drawable.icon_more);
//                        recyclerView.setVisibility(View.GONE);
//                        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
//                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//                                | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//                        isEmoji = false;
//                    }
//                } else {
//                    //  输入法关闭状态下
//                    if (!isEmoji) {
//                        //                            mSwitchBtn.setBackgroundResource(R.drawable.icon_key);
//                        // 设置为不会调整大小，以便输入弹起时布局不会改变。若不设置此属性，输入法弹起时布局会闪一下
//                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
//                        recyclerView.setVisibility(View.VISIBLE);
//                        isEmoji = true;
//                    } else {
//                        //                            mSwitchBtn.setBackgroundResource(R.drawable.icon_more);
//                        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
//                        isEmoji = false;
//                        imageView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() { // 输入法弹出之后，重新调整
//                                recyclerView.setVisibility(View.GONE);
//                                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//                                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//                            }
//                        }, 250); // 延迟一段时间，等待输入法完全弹出*/
//                    }
//                }
//            }
//        });
//
//
//        editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                editText.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        recyclerView.setVisibility(View.GONE);
//                        // 输入法弹出之后，重新调整
//                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//                                | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//                    }
//                }, 250); // 延迟一段时间，
//            }
//        });
//    }
//
//    private class KeyboardOnGlobalChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
//        int mScreenHeight = 0;
//        Rect mRect = new Rect();
//
//        private int getScreenHeight() {
//            if (mScreenHeight > 0) {
//                return mScreenHeight;
//            }
//            mScreenHeight = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
//                    .getDefaultDisplay().getHeight();
//            return mScreenHeight;
//        }
//
//        @Override
//        public void onGlobalLayout() {
//            // 获取当前页面窗口的显示范围
//            getWindow().getDecorView().getWindowVisibleDisplayFrame(mRect);
//            int screenHeight = getScreenHeight();
//            int keyboardHeight = screenHeight - mRect.bottom; // 输入法的高度
//            boolean isActive = false;
//            if (Math.abs(keyboardHeight) > screenHeight / 5) {
//                isActive = true; // 超过屏幕五分之一则表示弹出了输入法
//            }
//            mIsKeyboardActive = isActive;
//            onKeyboardStateChanged(mIsKeyboardActive, keyboardHeight);
//        }
//    }
//
//    public void hideKeyboard() {
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
//    }
//
//    private void onKeyboardStateChanged(boolean isActive, int keyboardHeight) {
//        if (isActive) {
//
//            editText.requestFocus();
//            if (isEmoji) { // 表情打开状态下
//                recyclerView.setVisibility(View.GONE);
//                isEmoji = false;
//            }
////            SPUtils.saveInt(mContext,  COLUMN_NAME, keyboardHeight);
//            ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
//            if (!(params.height == keyboardHeight)) {
//                params.height = keyboardHeight;
//                recyclerView.setLayoutParams(params);
//            }
//        } else {
//            if (isEmoji) {
//                return;
//            }
//        }
//    }
//
//}
