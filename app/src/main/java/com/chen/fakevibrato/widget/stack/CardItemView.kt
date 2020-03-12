package com.chen.fakevibrato.widget.stack

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.dynamicanimation.animation.SpringAnimation

class CardItemView constructor(mContext : Context) : FrameLayout(mContext){
    init {
        initSpring()

    }

    private fun initSpring(){


    }

    fun onStartDragging(){

    }

    fun setParentView(view : View){}
    fun bindLayoutResId(layoutResId : Int){
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(layoutResId, null)
        addView(view, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
    }

    fun setVisibilityWithAnimation(visibility : Int, delayIndex : Int){

    }
}