package com.chen.annotationutils

import android.app.Activity



class AnnotationUtils {

    companion object{
        val mInstance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            AnnotationUtils()
        }
    }

    fun inject(activity: Activity) {

    }


}