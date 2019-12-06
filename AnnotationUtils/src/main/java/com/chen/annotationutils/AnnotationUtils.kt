package com.chen.annotationutils

import android.app.Activity
import android.util.Log

class AnnotationUtils {

    companion object{
        val mInstance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            AnnotationUtils()
        }
    }

    fun inject(activity: Activity) {
        injectObject(activity)
    }

    private fun injectObject(target : Any){
        var targetClass = target.javaClass
        val methods = targetClass.declaredMethods
        for (method in methods) {
            var checkNet = method.getAnnotation(CheckNet::class.java)
            if (checkNet != null){
               Log.e("AnnotationUtils","点击了 ")
            }
        }
    }
}