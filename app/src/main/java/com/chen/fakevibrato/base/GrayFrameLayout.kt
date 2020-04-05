package com.chen.fakevibrato.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * @author Created by CHEN on 2020/4/5
 * @email 188669@163.com
 * APP灰度
 */
class GrayFrameLayout : FrameLayout{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}