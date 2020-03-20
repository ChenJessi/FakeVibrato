package com.chen.fakevibrato.widget.stack

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.chen.fakevibrato.utils.MyLog
import com.chen.fakevibrato.widget.anim.AnimtorUtils

class CardItemView  : FrameLayout{

    private lateinit var xSpringAnimation : SpringAnimation
    private lateinit var ySpringAnimation : SpringAnimation
    private lateinit var springForce : SpringForce
    init {
//        initSpring()
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun initSpring(finalX: Int, finalY: Int){
//        var posiY =  finalY - top
        springForce = SpringForce(0f).apply {
            dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            stiffness = SpringForce.STIFFNESS_LOW
        }
//                .setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_PIXELS)
        xSpringAnimation = SpringAnimation(this, SpringAnimation.TRANSLATION_X, 0f).setStartVelocity(500f).setSpring(springForce)
        ySpringAnimation = SpringAnimation(this, SpringAnimation.TRANSLATION_Y, 0f).setStartVelocity(500f).setSpring(springForce)

        xSpringAnimation.start()
        ySpringAnimation.start()

    }

    fun onStartDragging(){

    }

    fun setParentView(view : View){}
    fun bindLayoutResId(layoutResId : Int){

        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(layoutResId, null)
//        var random = java.util.Random()
//        val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
//        var text = view.findViewById<TextView>(R.id.textView)
//        text.setBackgroundColor(color)
        addView(view, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
    }

    /**
     * 移动到指定位置
     */
    fun moveAnimTo(scrollToX : Int, scrollToY : Int){
        MyLog.e("scrollToY    $scrollToY")
        var anim  = AnimtorUtils.translationY(this, 0f, -200f, 300, 0, LinearInterpolator())
        anim.start()
//        x = scrollToX.toFloat()
//        y = scrollToY.toFloat()
//        initSpring(scrollToX, scrollToY)
//        xSpringAnimation.start()
//        ySpringAnimation.start()
//        SpringAnimation(this, DynamicAnimation.TRANSLATION_X).apply {
//            this.setStartVelocity(4000f)
//            val spring = SpringForce(0f)
//            spring.stiffness = SpringForce.STIFFNESS_LOW
//            spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
//            this.spring = spring
//            this.minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_PIXELS
//        }.start()
    }
    fun setVisibilityWithAnimation(visibility : Int, delayIndex : Int){

    }
}