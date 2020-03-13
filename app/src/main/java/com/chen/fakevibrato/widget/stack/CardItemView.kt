package com.chen.fakevibrato.widget.stack

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.dynamicanimation.animation.SpringAnimation
import com.chen.fakevibrato.R
import com.chen.fakevibrato.utils.MyLog

class CardItemView  : FrameLayout{

    init {
        initSpring()

    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){

    }

    private fun initSpring(){


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

    fun setVisibilityWithAnimation(visibility : Int, delayIndex : Int){

    }
}