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
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import kotlin.math.atan
import android.R.attr.y
import android.R.attr.x
import android.animation.*
import android.view.animation.OvershootInterpolator
import com.chen.fakevibrato.R
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.view.ViewTreeObserver


/**
 * 拖拽气泡
 * 只完成绘制 爆炸效果
 * 具体状态判断逻辑未完成
 */
class MessageBubbleView : View {
    private lateinit var mDragPoint : PointF
    //触摸圆半径
    private var mDragRadius = 30f
    private lateinit var mFixationPoint : PointF
    private var mPaint: Paint = Paint()
    private var mFixationRadius = 0f
    private var FIXATION_RADIUS_MIN = 8f
    private var FIXATION_RADIUS_MAX = 20f

    private var broken = false
    private var mBurstPaint : Paint
    private var mBurstRect  : Rect
    private var mCurDrawableIndex = 0
    /**
     * 气泡爆炸的图片id数组
     */
    private val mBurstDrawablesArray = intArrayOf(R.mipmap.burst_1, R.mipmap.burst_2, R.mipmap.burst_3, R.mipmap.burst_4, R.mipmap.burst_5)
    private val mBurstBitmapsArray = arrayOfNulls<Bitmap>(5)
    /**
     * 状态
     * 还原&爆炸
     */
    private val STATE_FIXATION = 0
    private val STATE_BROKEN = 1
    private val STATE_INIT = 2
    private var STATE_TYPE = STATE_INIT

    // 连接部分是否拉断
    private var fracture = false

    private var mTextRect : Rect
    private var mTextPaint : Paint

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    private var textStr = "99"

    init {
        mPaint.isAntiAlias = true
        mPaint.color = Color.RED

        //初始化爆炸画笔
        mBurstPaint = Paint()
        mBurstPaint.isAntiAlias = true
        mBurstPaint.isFilterBitmap = true
        mBurstRect =  Rect()

        mTextRect = Rect()
        mTextPaint = Paint()
        mTextPaint.isAntiAlias = true
        mTextPaint.color = Color.WHITE
        mTextPaint.textSize = 40f
        mTextPaint.getTextBounds(textStr, 0, textStr.length, mTextRect)

        for (i in 0 until mBurstDrawablesArray.size){
            val bitmap = BitmapFactory.decodeResource(context.resources, mBurstDrawablesArray[i])
            mBurstBitmapsArray[i] = bitmap
        }

    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mFixationPoint = PointF(((( right - left) / 2).toFloat()), ((bottom - top ) / 2).toFloat())
    }
    override fun onDraw(canvas: Canvas?) {
        if (!this::mDragPoint.isInitialized || !this::mFixationPoint.isInitialized){
            canvas?.drawCircle(mFixationPoint.x, mFixationPoint.y, mDragRadius, mPaint)
            canvas?.drawText(textStr, mFixationPoint.x - mTextRect.width() / 2, mFixationPoint.y + mTextRect.height() / 2, mTextPaint)
            return
        }

        if (broken){
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

        if (mDragPoint != mFixationPoint){

            // 连接部分是否拉断
            if (!fracture){
                var path = getBezierPath()
                path?.let { canvas?.drawPath(it, mPaint) }
            }
            //连接部分没有拉断   &&  最小值  < 半径 <  最大值
            if (!fracture  && mFixationRadius > FIXATION_RADIUS_MIN  && mFixationRadius < FIXATION_RADIUS_MAX ){
                canvas?.drawCircle(mFixationPoint.x, mFixationPoint.y, mFixationRadius, mPaint)
            }
        }

        canvas?.drawCircle(mDragPoint.x, mDragPoint.y, mDragRadius, mPaint)
        canvas?.drawText(textStr, mDragPoint.x - mTextRect.width() / 2, mDragPoint.y + mTextRect.height() / 2, mTextPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                initPoint(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                mDragPoint.set(event.x, event.y)
                if (mFixationRadius > 0 &&  mFixationRadius < FIXATION_RADIUS_MIN){
                    fracture = true
                }
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                if (mFixationRadius > FIXATION_RADIUS_MIN ){
                    STATE_TYPE = STATE_FIXATION
                    animToOrigin()
                }else{
                    STATE_TYPE = STATE_BROKEN
                    broken()
                }

            }
        }

        return true
    }


    private fun initPoint(eventX : Float, eventY : Float){
        mDragPoint = PointF(eventX, eventY)
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

    private fun setDragPoint(point: PointF){
        mDragPoint.set(point)
    }

    /**
     * 放手之后弹回的角度
     */
    private fun animToOrigin(){
        val anim = ObjectAnimator.ofObject(this, "dragPoint", PointFEvaluator(), mDragPoint, mFixationPoint)

        anim.addUpdateListener {
            mDragPoint = it.animatedValue as PointF

            invalidate()
        }

        anim.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                restore()
            }
        })
        anim.interpolator = OvershootInterpolator(2f)
        anim.duration = 150
        anim.start()


    }


    private fun broken() {
        broken = true

        val anim = ValueAnimator.ofInt(0, mBurstDrawablesArray.size )
        anim.duration = 300
        anim.interpolator = LinearInterpolator()
        anim.addUpdateListener { animation ->
            mCurDrawableIndex  = animation.animatedValue as Int
            invalidate()
        }
        anim.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                visibility = GONE
                restore()
            }
        })
        anim.start()
    }

    private fun restore(){
        STATE_TYPE = STATE_INIT
        fracture = false
        broken = false
    }
}