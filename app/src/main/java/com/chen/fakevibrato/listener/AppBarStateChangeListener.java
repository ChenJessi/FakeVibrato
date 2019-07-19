package com.chen.fakevibrato.listener;

import com.chen.fakevibrato.utils.MyLog;
import com.google.android.material.appbar.AppBarLayout;

/**
 * @author Created by CHEN on 2019/7/19
 * @email 188669@163.com
 * <p>
 * AppBarLayout 状态监听
 */
public  abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        //展开
        EXPANDED,
        //折叠
        COLLAPSED,
        //中间
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }

    public abstract  void onStateChanged(AppBarLayout appBarLayout, State state);
}
