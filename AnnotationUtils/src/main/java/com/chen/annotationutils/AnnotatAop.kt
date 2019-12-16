package com.chen.annotationutils

import android.content.Context
import android.util.Log
import android.widget.Toast
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature


@Aspect
class AnnotatAop {

    @Pointcut("execution(@com.chen.annotationutils.CheckNet * *(..))")
    fun CheckNet(){
    }

    @Around("CheckNet()")//环绕通知，先执行通知
    @Throws(Throwable::class)//可能抛出的异常
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint){
        val methodSignature = joinPoint.signature as MethodSignature
        val checkLogin : CheckNet? = methodSignature.method.getAnnotation(CheckNet::class.java)
        if(checkLogin != null){
            val context = joinPoint.`this` as Context
            if(AnnotatUtils.networkAvailable(context)){    // 执行对应的内容
                joinPoint.proceed()//执行标注的方法中的内容
            }else{
                Toast.makeText(context,"网络开小差了哦", Toast.LENGTH_SHORT).show()
            }
        }
    }
}