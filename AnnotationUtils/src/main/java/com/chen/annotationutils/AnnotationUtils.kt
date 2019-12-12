package com.chen.annotationutils

import android.app.Activity
import android.util.Log
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object AnnotationUtils {
    private val TAG = this.javaClass.simpleName
//    companion object{
//        val mInstance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
//            AnnotationUtils()
//        }
//    }

    fun inject(activity: Activity) {
        injectObject(activity)

    }
    //object --> 反射需要执行的类


    private fun injectObject(target: Any) {
        var targetClass = target.javaClass
        val methods = targetClass.declaredMethods
        for (method in methods) {
            var checkNet = method.getAnnotation(CheckNet::class.java)

        }
    }

}