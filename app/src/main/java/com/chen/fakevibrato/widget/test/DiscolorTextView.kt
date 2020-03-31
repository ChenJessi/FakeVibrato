package com.chen.fakevibrato.widget.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView

class DiscolorTextView : TextView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mOriginPaint = Paint()
    private var mChangePaint = Paint()
    private var testPaint = Paint()
    init {
        getColorPaint(mOriginPaint, Color.GRAY)
        getColorPaint(mChangePaint, Color.RED)
        getColorPaint(testPaint, Color.YELLOW)
    }
    override fun onDraw(canvas: Canvas?) {

        canvas?.let { drawText(it, mChangePaint, 0, measuredWidth / 4) }
        canvas?.let { drawText(it, mOriginPaint, measuredWidth / 4, measuredWidth /2 ) }
        canvas?.let { drawText(it, testPaint, measuredWidth / 2, measuredWidth  ) }
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
        var fontMetrics  = paint?.fontMetricsInt
        var fontTotalHeight  = (fontMetrics?.bottom ?: 0) - (fontMetrics?.top ?: 0)
        var offY = fontTotalHeight / 2 - (fontMetrics?.bottom ?: 0)
        val baseline = (measuredHeight + fontTotalHeight) / 2 - offY

        canvas.drawText(text.toString(), 0f, baseline.toFloat(), paint)
        canvas.restore()
    }
}