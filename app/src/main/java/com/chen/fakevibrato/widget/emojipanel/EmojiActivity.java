package com.chen.fakevibrato.widget.emojipanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.util.IndianCalendar;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.emojiview.EmojiconEditText;
import com.chen.fakevibrato.widget.pagergrid.PagerGridLayoutManager;
import com.chen.fakevibrato.widget.pagergrid.PagerGridSnapHelper;
import com.qmuiteam.qmui.util.QMUIKeyboardHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.rd.PageIndicatorView;

import cn.dreamtobe.kpswitch.util.KPSwitchConflictUtil;
import cn.dreamtobe.kpswitch.util.KeyboardUtil;
import cn.dreamtobe.kpswitch.widget.KPSwitchFSPanelFrameLayout;
import cn.dreamtobe.kpswitch.widget.KPSwitchFSPanelLinearLayout;
import cn.dreamtobe.kpswitch.widget.KPSwitchFSPanelRelativeLayout;

/**
 * 表情工具面板view
 */
public class EmojiActivity extends AppCompatActivity {
    private LinearLayout rootView;
    private RecyclerView recyclerView;
    private EmojiconEditText editText;
    private ImageView ivAt;
    private ImageView ivEmoji;
    private ImageView ivSend;
    private PageIndicatorView indicatorView;
    private boolean isEmiji = false;
    private KPSwitchFSPanelRelativeLayout mPanelLayout;
    private EmojiAdapter adapter;
    private boolean isFirst = true;
    private boolean isKeyboard = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_emoji);

        recyclerView = findViewById(R.id.recyclerView);
        rootView = findViewById(R.id.rootView);
        ivEmoji = findViewById(R.id.ivEmoji);
        ivSend = findViewById(R.id.ivSend);
        ivAt = findViewById(R.id.ivAt);
        editText = findViewById(R.id.editText);
        mPanelLayout = findViewById(R.id.panel_root);
        indicatorView = findViewById(R.id.indicatorView);
        KeyboardUtil.attach(this, mPanelLayout);

        PagerGridLayoutManager manager = new PagerGridLayoutManager(5, 8, PagerGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        PagerGridSnapHelper pagerSnapHelper = new PagerGridSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
        adapter = new EmojiAdapter(EmojiActivity.this);

        initListener();

        KPSwitchConflictUtil.showPanel(mPanelLayout);
        KeyboardUtil.showKeyboard(editText);

        manager.setPageListener(new PagerGridLayoutManager.PageListener() {
            @Override
            public void onPageSizeChanged(int pageSize) {
                indicatorView.setCount(pageSize);
            }

            @Override
            public void onPageSelect(int pageIndex) {
                indicatorView.setSelection(pageIndex);
            }
        });
    }

    private void initListener() {
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
                isKeyboard = true;
                ivEmoji.setImageResource(R.mipmap.emoji);
                KPSwitchConflictUtil.showPanel(mPanelLayout);
                KeyboardUtil.showKeyboard(editText);
                //KPSwitchConflictUtil.showKeyboard(mPanelLayout, editText);
            }
        });
        ivEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isKeyboard){
                    isKeyboard = false;
                    ivEmoji.setImageResource(R.mipmap.keyboard);
                    if (isFirst){
                        isFirst = false;
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(adapter);
                    }else {
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    isEmiji = true;
                    KPSwitchConflictUtil.showPanel(mPanelLayout);
                    KeyboardUtil.hideKeyboard(editText);
                }else {
                    isKeyboard = true;
                    ivEmoji.setImageResource(R.mipmap.emoji);
                    KPSwitchConflictUtil.showPanel(mPanelLayout);
                    KeyboardUtil.showKeyboard(editText);
                }
            }
        });
        /**
         * 软键盘监听处理
         */
        QMUIKeyboardHelper.setVisibilityEventListener(EmojiActivity.this, new QMUIKeyboardHelper.KeyboardVisibilityEventListener() {
            @Override
            public boolean onVisibilityChanged(boolean isOpen, int heightDiff) {
                MyLog.d("isOpen : "+isOpen);
                if (!isOpen && !isEmiji) {
                    KPSwitchConflictUtil.hidePanelAndKeyboard(mPanelLayout);
                    finish();
                } else if (isOpen){
                    recyclerView.setVisibility(View.INVISIBLE);
                }
                isEmiji = false;
                return false;
            }
        });

        adapter.setOnItemClickListener(new EmojiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String emoji) {
                editText.append(emoji);
            }
        });
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtil.hideKeyboard(editText);
                KPSwitchConflictUtil.hidePanelAndKeyboard(mPanelLayout);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 用于记录当前的键盘状态，在从后台回到当前页面的时候，键盘状态能够正确的恢复并且不会导致布局冲突。
        mPanelLayout.recordKeyboardStatus(getWindow());
    }

    // 如果需要处理返回收起面板的话
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
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
