package com.chen.fakevibrato.widget.rowing

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import com.chen.fakevibrato.R

/**
 * 小船滑动View
 */
class RowingView : ViewGroup {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val mBoat = ImageView(context)
    private val mMan = ImageView(context)
    private val mPaddle1 = ImageView(context)
    private val mPaddle2 = ImageView(context)

    private var mAngle = 0f
    private var isAdd = true

    init {
        mBoat.setImageResource(R.drawable.rowing_01)
        mBoat.layout(0, 0, 200, 200)
        addView(mBoat)

        mPaddle1.setImageResource(R.drawable.rowing_02)
        mPaddle1.layout(50, 50, 150, 120)
        mPaddle1.pivotX = mPaddle1.pivotX - 15f
        addView(mPaddle1)

        mPaddle2.setImageResource(R.drawable.rowing_02)
        mPaddle2.layout(50, 80, 150, 150)
        mPaddle2.pivotX = mPaddle2.pivotX - 15f
        addView(mPaddle2)
        
        mMan.setImageResource(R.drawable.rowing_03)
        mMan.layout(70, 80, 110, 120)
        addView(mMan)
        setWillNotDraw(false)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {}

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (mAngle > 90){
            isAdd = false
        }
        if (mAngle <= 0){
            isAdd = true
        }

        mPaddle1.rotation = 135f - mAngle
        mPaddle2.rotation = -135f + mAngle

        if (isAdd){
            mAngle += 2f
        }else{
            mAngle -= 2f
        }
        mMan.translationX = mAngle / 5

        invalidate()
    }

}