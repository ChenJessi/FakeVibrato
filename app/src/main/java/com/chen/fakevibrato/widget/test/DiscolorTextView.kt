package com.chen.fakevibrato.widget.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.TextView

class DiscolorTextView : TextView {
    //进度百分百
    private var mCurrentProgress = 0.3f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mOriginPaint = Paint()
    private var mChangePaint = Paint()
    private var testPaint = Paint()
    init {
        getColorPaint(mOriginPaint, Color.GRAY)
        getColorPaint(mChangePaint, Color.RED)
    }
    override fun onDraw(canvas: Canvas?) {
        var mCurrentX = mCurrentProgress * measuredWidth
        canvas?.let { drawText(it, mChangePaint, 0, mCurrentX.toInt()) }
        canvas?.let { drawText(it, mOriginPaint, mCurrentX.toInt(), measuredWidth ) }
    }

    private fun getColorPaint(paint: Paint ,color: Int){
        paint.color = color
        paint.isAntiAlias = true
        paint.textSize = 120f
        paint.strokeWidth = 20f
    }
    private fun drawText(canvas : Canvas , paint: Paint? , startX : Int, endX : Int){
        canvas.save()
        canvas.clipRect(startX, 0, endX, measuredHeight)
        //文字绘制区域
        val bounds = Rect()
        mOriginPaint.getTextBounds(text.toString(), 0, text.toString().length, bounds)

        var fontMetrics  = paint?.fontMetricsInt
        var fontTotalHeight  = (fontMetrics?.bottom ?: 0) - (fontMetrics?.top ?: 0)
        var offY = fontTotalHeight / 2 - (fontMetrics?.bottom ?: 0)
        val baseline = (measuredHeight + fontTotalHeight) / 2 - offY
        //居中绘制
        canvas.drawText(text.toString(), (getMeasuredWidth() / 2 - bounds.width() / 2).toFloat(), baseline.toFloat(), paint)
        canvas.restore()
    }
}