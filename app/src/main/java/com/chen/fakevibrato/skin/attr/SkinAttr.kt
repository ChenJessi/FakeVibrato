package com.chen.fakevibrato.skin.attr

import android.view.View

class SkinAttr constructor(var mResName : String, var mSkinType : SkinType){

    fun skin(view : View){
        mSkinType.skin(view,mResName)
    }
}