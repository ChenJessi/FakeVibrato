package com.chen.fakevibrato.utils;

import android.content.Context;

/**
 * @author Created by CHEN on 2019/6/12
 * @email 188669@163.com
 */
public class DisplayUtils {

    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
