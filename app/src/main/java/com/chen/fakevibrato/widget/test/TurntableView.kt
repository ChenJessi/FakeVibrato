package com.chen.fakevibrato.widget.test

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.chen.fakevibrato.ui.my.contract.UserContract
import android.R.attr.x
import android.R.attr.y
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.sqrt

/**
 * Turntable
 */
class TurntableView : View {
     private var mWidth = 0
     private var mHeight = 0
    private var lastAngle = 0f
    private var newAngle = 0f
    private val MAX_VALUE = 100f
    private var lastValue = 0f
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight

    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint = Paint(ANTI_ALIAS_FLAG)
        paint.isAntiAlias = true
        paint.color = Color.RED
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE

        var rectF = RectF(100f, (mHeight.toFloat() - mWidth.toFloat() + 200) / 2, mWidth.toFloat() - 100, (mHeight.toFloat() + mWidth.toFloat() - 200) / 2)
        canvas?.drawArc(rectF, 180f + newAngle, 360f, false, paint)
        canvas?.save()


        var paintLine = Paint(ANTI_ALIAS_FLAG)
        paintLine.color = Color.BLUE
        paintLine.strokeWidth = 2f
        canvas?.rotate(newAngle ,  mWidth.toFloat() / 2 , mHeight.toFloat() / 2)
        canvas?.drawLine(100f, mHeight.toFloat() / 2 , 200f, mHeight.toFloat() / 2  ,  paintLine)
        var angle = 360 / MAX_VALUE
        canvas?.rotate(  angle ,  mWidth.toFloat() / 2 , mHeight.toFloat() / 2)
        canvas?.drawLine(100f, mHeight.toFloat() / 2, 200f, mHeight.toFloat() / 2  ,  paintLine)
//        for (i in 0 until MAX_VALUE.toInt()){
//            canvas?.rotate(  angle,  mWidth.toFloat() / 2 , mHeight.toFloat() / 2)
//            canvas?.drawLine(100f, mHeight.toFloat() / 2, 200f, mHeight.toFloat() / 2  ,  paintLine)
//        }
        canvas?.restore()
    }

    private var downPoint = Point()
    private var cenPoint  = Point()
    private var movePoint  = Point()
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                downPoint.set(event.x.toInt(), event.y.toInt())
            }
            MotionEvent.ACTION_MOVE -> {
                cenPoint.set(mWidth / 2, mHeight / 2)
                movePoint.set(event.x.toInt(), event.y.toInt())

                newAngle = lastAngle + angle(cenPoint, downPoint, movePoint)
                if (newAngle > 0){
                    if (newAngle > 360){
                        newAngle %= 360
                    }
                    lastValue = MAX_VALUE * (1 - newAngle / 360f)
                }else{
                    if (abs(newAngle) > 360){
                        newAngle %= 360
                    }
                    lastValue = MAX_VALUE *  (1 - abs(newAngle )/ 360f)
                }
                invalidate()
            }
            MotionEvent.ACTION_UP ->{
                lastAngle = newAngle
            }
        }

        return true
    }


    /**
     * 根据坐标系中的3点确定夹角的方法（注意：夹角是有正负的）
     */
    private fun angle(cen: Point, first: Point, second: Point): Float {
        val dx1: Float = (first.x - cen.x).toFloat()
        val dx2: Float = (second.x - cen.x).toFloat()
        val dy1: Float = (first.y - cen.y).toFloat()
        val dy2: Float = (second.y - cen.y).toFloat()

        // 计算三边的平方
        val ab2 = (second.x - first.x) * (second.x - first.x) + (second.y - first.y) * (second.y - first.y)
        val oa2 = dx1 * dx1 + dy1 * dy1
        val ob2 = dx2 * dx2 + dy2 * dy2

        // 根据两向量的叉乘来判断顺逆时针
        val isClockwise = (first.x - cen.x) * (second.y - cen.y) - (first.y - cen.y) * (second.x - cen.x) > 0
        // 根据余弦定理计算旋转角的余弦值
        // 任何一边的平方等于其他两边平方的和减去这两边与它们夹角的余弦的两倍积
        // cosA = (ab^2 + ac^2 - bc^2 ) / 2 ab * ac
        var cosDegree = (oa2 + ob2 - ab2) / (2.0 * sqrt(oa2.toDouble()) * sqrt(ob2.toDouble()))

        // 异常处理，因为算出来会有误差绝对值可能会超过一，所以需要处理一下
        if (cosDegree > 1) {
            cosDegree = 1.0
        } else if (cosDegree < -1) {
            cosDegree = -1.0
        }
        // 计算弧度
        val radian = acos(cosDegree)

        // 计算角度，顺时针为正，逆时针为负
        return (if (isClockwise) Math.toDegrees(radian) else -Math.toDegrees(radian)).toFloat()
    }
}