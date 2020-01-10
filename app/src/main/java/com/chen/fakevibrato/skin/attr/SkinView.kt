package com.chen.fakevibrato.skin.attr

import android.view.View

class SkinView (var mView : View, var mSkinAttrs: List<SkinAttr>){

    fun skin(){
        for (attr in mSkinAttrs) {
            attr.skin(mView)
        }
    }
}