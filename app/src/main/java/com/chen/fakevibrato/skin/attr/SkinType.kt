package com.chen.fakevibrato.skin.attr

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chen.fakevibrato.skin.SkinResource

enum class SkinType(var mResName: String) {
    TEXT_COLOR("textColor") {
        override fun skin(view: View, resName: String) {
            var skinResource = getSkinResource()
            var color: ColorStateList? = skinResource.getColorByName(resName) ?: return
            var textView = view as TextView
            textView.setTextColor(color)
        }
    },

    BACKGROUND("background"){
        override fun skin(view: View, resName: String) {
            //背景可能是图片也可能是颜色
            var skinResource = getSkinResource()
            var drawable = skinResource.getDrawableByName(resName)
            if (drawable != null){
                var imageView = view as ImageView
                imageView.background = drawable
                return
            }
            //可能是颜色
            var color = skinResource.getColorByName(resName)
            if (color != null){
                view.setBackgroundColor(color.defaultColor)
            }
        }
    },

    SRC("src"){
        override fun skin(view: View, resName: String) {
            //获取资源设置
            var skinResource = getSkinResource()
            var drawable = skinResource.getDrawableByName(resName)
            if (drawable != null){
                var imageView = view as ImageView
                imageView.setImageDrawable(drawable)
                return
            }
        }

    };

    fun getSkinResource() : SkinResource{
        return
    }

    fun getResName() : String{
        return mResName
    }
    abstract fun skin(view: View, resName: String)
}