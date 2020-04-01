package com.chen.fakevibrato.widget.test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.chen.fakevibrato.utils.MyLog
import kotlin.math.sqrt
import android.R.attr.y
import android.R.attr.x
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import kotlin.math.atan
import android.R.attr.y
import android.R.attr.x
import android.view.animation.OvershootInterpolator
import com.chen.fakevibrato.R
import android.graphics.Paint.ANTI_ALIAS_FLAG


/**
 * 拖拽气泡
 * 只完成绘制 爆炸效果
 * 具体状态判断逻辑未完成
 */
class MessageBubbleView : View {
    private lateinit var mDragPoint : PointF
    //触摸圆半径
    private var mDragRadius = 20f
    private lateinit var mFixationPoint : PointF
    private var mPaint: Paint = Paint()
    private var mFixationRadius = 0f
    private var FIXATION_RADIUS_MIN = 8f
    private var FIXATION_RADIUS_MAX = 20f

    private var broken = false
    private var brokenProgress = 0f
    private var mBurstPaint : Paint
    private var mBurstRect  : Rect
    private var mCurDrawableIndex = 0
    /**
     * 气泡爆炸的图片id数组
     */
    private val mBurstDrawablesArray = intArrayOf(R.mipmap.burst_1, R.mipmap.burst_2, R.mipmap.burst_3, R.mipmap.burst_4, R.mipmap.burst_5)
    private val mBurstBitmapsArray = arrayOfNulls<Bitmap>(5)


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        mPaint.isAntiAlias = true
        mPaint.color = Color.RED

        //初始化爆炸画笔
        mBurstPaint = Paint()
        mBurstPaint.isAntiAlias = true
        mBurstPaint.isFilterBitmap = true
        mBurstRect =  Rect()

        for (i in 0 until mBurstDrawablesArray.size){
            val bitmap = BitmapFactory.decodeResource(context.resources, mBurstDrawablesArray[i])
            mBurstBitmapsArray[i] = bitmap
        }
    }


    override fun onDraw(canvas: Canvas?) {
        if (!this::mDragPoint.isInitialized || !this::mFixationPoint.isInitialized){
            return
        }
        if (broken){
//            val dr = mDragRadius / 2 + mDragRadius * 4 * (brokenProgress / 100f)
//            canvas?.drawCircle(mDragPoint.x, mDragPoint.y, mDragRadius / (brokenProgress + 1), mPaint)
//            canvas?.drawCircle(mDragPoint.x - dr, mDragPoint.y - dr, mDragRadius / (brokenProgress + 2), mPaint)
//            canvas?.drawCircle(mDragPoint.x + dr, mDragPoint.y - dr, mDragRadius / (brokenProgress + 2), mPaint)
//            canvas?.drawCircle(mDragPoint.x - dr, mDragPoint.y + dr, mDragRadius / (brokenProgress + 2), mPaint)
//            canvas?.drawCircle(mDragPoint.x + dr, mDragPoint.y + dr, mDragRadius / (brokenProgress + 2), mPaint)
            if (mCurDrawableIndex >= mBurstBitmapsArray.size){
                return
            }
            mBurstRect.set(
                    ((mDragPoint.x - mDragRadius * 2).toInt()),
                    ((mDragPoint.y - mDragRadius * 2).toInt()),
                    ((mDragPoint.x + mDragRadius * 2).toInt()),
                    ((mDragPoint.y + mDragRadius * 2).toInt())
            )
            canvas?.drawBitmap(mBurstBitmapsArray[mCurDrawableIndex],null,mBurstRect,mBurstPaint);
            return
        }
        var distance = getDistance(mDragPoint, mFixationPoint)

         mFixationRadius = (FIXATION_RADIUS_MAX - distance / 15).toFloat()

        canvas?.drawCircle(mDragPoint.x, mDragPoint.y, mDragRadius, mPaint)
        var path = getBezierPath()
        path?.let { canvas?.drawPath(it, mPaint) }

        if (mFixationRadius > FIXATION_RADIUS_MIN && mFixationPoint.x != mDragPoint.x && mFixationPoint.y != mDragPoint.y){
            canvas?.drawCircle(mFixationPoint.x, mFixationPoint.y, mFixationRadius, mPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                initPoint(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                mDragPoint.set(event.x, event.y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                if (mFixationRadius > FIXATION_RADIUS_MIN ){
                    animToOrigin()
                }else{
                    broken()
                }

            }
        }

        return true
    }


    private fun initPoint(x : Float, y : Float){
        mDragPoint = PointF(x, y)
        mFixationPoint = PointF(x, y)
    }

    /**
     * 计算两点距离
     */
    private fun getDistance(point1 : PointF, point2 : PointF) : Double{
        var ox = point2.x - point1.x
        var oy = point2.y - point1.y
        return sqrt((ox * ox + oy * oy).toDouble())
    }

    /**
     *计算 路径
     */
    private fun getBezierPath() : Path? {
        if (mFixationRadius < FIXATION_RADIUS_MIN) {
            return null
        }

        val bezierPath = Path()

        // 计算斜率
        var dx = mFixationPoint.x - mDragPoint.x
        val dy = mFixationPoint.y - mDragPoint.y
        if (dx == 0f) {
            dx = 0.001f
        }
        val tan = dy / dx
        // 获取角a度值
        val arcTanA = atan(tan.toDouble()).toFloat()

        // 依次计算 p0 , p1 , p2 , p3 点的位置
        val P0X = (mFixationPoint.x + mFixationRadius * Math.sin(arcTanA.toDouble())).toFloat()
        val P0Y = (mFixationPoint.y - mFixationRadius * Math.cos(arcTanA.toDouble())).toFloat()

        val P1X = (mDragPoint.x + mDragRadius * Math.sin(arcTanA.toDouble())).toFloat()
        val P1Y = (mDragPoint.y - mDragRadius * Math.cos(arcTanA.toDouble())).toFloat()

        val P2X = (mDragPoint.x - mDragRadius * Math.sin(arcTanA.toDouble())).toFloat()
        val P2Y = (mDragPoint.y + mDragRadius * Math.cos(arcTanA.toDouble())).toFloat()

        val P3X = (mFixationPoint.x - mFixationRadius * Math.sin(arcTanA.toDouble())).toFloat()
        val P3Y = (mFixationPoint.y + mFixationRadius * Math.cos(arcTanA.toDouble())).toFloat()
        // 求控制点 两个点的中心位置作为控制点
        val controlPoint = getPointByPercent(mDragPoint, mFixationPoint, 0.5f)

        // 整合贝塞尔曲线路径
        bezierPath.moveTo(P0X, P0Y)
        bezierPath.quadTo(controlPoint.x, controlPoint.y, P1X, P1Y)
        bezierPath.lineTo(P2X, P2Y)
        bezierPath.quadTo(controlPoint.x, controlPoint.y, P3X, P3Y)
        bezierPath.close()

        return bezierPath
    }

    private fun getPointByPercent(point1: PointF, point2: PointF, percent : Float) : PointF{
        var x = (point1.x + point2.x ) * percent
        var y = (point1.y + point2.y ) * percent
        return PointF(x, y)
    }

    private fun setDragPointX(x: Float){
        mDragPoint.x = x
    }
    private fun setDragPointY(y: Float){
        mDragPoint.y = y
    }
    /**
     * 放手之后弹回的角度
     */
    private fun animToOrigin(){
        val animX = ObjectAnimator.ofFloat(this, "dragPointX", mDragPoint.x, mFixationPoint.x)
        val animY = ObjectAnimator.ofFloat(this, "dragPointY", mDragPoint.y, mFixationPoint.y)
        animX.addUpdateListener {
            invalidate()
        }
        animX.interpolator = OvershootInterpolator ()
        animX.duration = 300
        animX.start()
        animY.interpolator = OvershootInterpolator()
        animY.duration = 300
        animY.start()

    }


    private fun broken() {
        broken = true

        val anim = ValueAnimator.ofInt(0, mBurstDrawablesArray.size )
        anim.duration = 500
        anim.interpolator = LinearInterpolator()
        anim.addUpdateListener { animation ->
            mCurDrawableIndex  = animation.animatedValue as Int
            invalidate()
        }
        anim.start()
    }


}