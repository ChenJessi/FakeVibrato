package com.chen.fakevibrato.widget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.method.QwertyKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.utils.MyLog;
import com.qmuiteam.qmui.util.QMUIKeyboardHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import cn.dreamtobe.kpswitch.util.KPSwitchConflictUtil;
import cn.dreamtobe.kpswitch.util.KeyboardUtil;
import cn.dreamtobe.kpswitch.widget.KPSwitchFSPanelFrameLayout;
import cn.dreamtobe.kpswitch.widget.KPSwitchFSPanelLinearLayout;

/**
 * 表情工具面板
 */
public class EmojiActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editText;
    private ImageView ivAt;
    private ImageView ivEmoji;
    private ImageView ivSend;
    private boolean isEmiji = false;
    private KPSwitchFSPanelFrameLayout mPanelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_emoji);

        recyclerView = findViewById(R.id.recyclerView);
        ivEmoji = findViewById(R.id.ivEmoji);
        ivSend = findViewById(R.id.ivSend);
        ivAt = findViewById(R.id.ivAt);
        editText = findViewById(R.id.editText);
        mPanelLayout = findViewById(R.id.panel_root);
        KeyboardUtil.attach(this, mPanelLayout);
        KPSwitchConflictUtil.attach(mPanelLayout, ivEmoji, editText,
                new KPSwitchConflictUtil.SwitchClickListener() {
                    @Override
                    public void onClickSwitch(View v, boolean switchToPanel) {
                        if (switchToPanel) {
                            editText.clearFocus();
                        } else {
                            editText.requestFocus();
                        }
                    }
                });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KPSwitchConflictUtil.showPanel(mPanelLayout);
                KeyboardUtil.showKeyboard(editText);
                //KPSwitchConflictUtil.showKeyboard(mPanelLayout, editText);
            }
        });
        ivEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEmiji = true;
                KPSwitchConflictUtil.showPanel(mPanelLayout);
                KeyboardUtil.hideKeyboard(editText);
            }
        });
        QMUIKeyboardHelper.setVisibilityEventListener(EmojiActivity.this, new QMUIKeyboardHelper.KeyboardVisibilityEventListener() {
            @Override
            public boolean onVisibilityChanged(boolean isOpen, int heightDiff) {
                if (!isOpen && !isEmiji){
                    KPSwitchConflictUtil.hidePanelAndKeyboard(mPanelLayout);
                    finish();
                }
                isEmiji = false;
                return false;
            }
        });
        KPSwitchConflictUtil.showPanel(mPanelLayout);
        KeyboardUtil.showKeyboard(editText);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // 用于记录当前的键盘状态，在从后台回到当前页面的时候，键盘状态能够正确的恢复并且不会导致布局冲突。
        mPanelLayout.recordKeyboardStatus(getWindow());
    }

    // 如果需要处理返回收起面板的话
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        if (event.getAction() == KeyEvent.ACTION_UP &&
                event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            KeyboardUtil.hideKeyboard(editText);
            if (mPanelLayout.getVisibility() == View.VISIBLE) {
                KPSwitchConflictUtil.hidePanelAndKeyboard(mPanelLayout);
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

}
