package com.chen.fakevibrato.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import com.chen.fakevibrato.R

/**
 * @author Created by CHEN on 2020/4/5
 * @email 188669@163.com
 * APP灰度
 */
class GrayFrameLayout : FrameLayout{
    private var mPaint : Paint = Paint()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        var colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        mPaint.colorFilter = ColorMatrixColorFilter(colorMatrix)

    }

    override fun draw(canvas: Canvas?) {
        canvas?.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG)
        super.draw(canvas)
        canvas?.restore()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG)
        super.dispatchDraw(canvas)
        canvas?.restore()
    }
}