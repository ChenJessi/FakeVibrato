package com.chen.fakevibrato.http

import android.content.Context
import java.lang.Exception

interface EngineCallBack {
    //开始执行，在执行之前会回调的方法
    fun onPreExecute(context: Context, params : Map<String, Any>?)

    //错误
    fun onError(e : Exception)

    //成功   成功  data{"",""}  失败  data:""
    fun onSuccess(result : String)

    companion object{
        // 默认的
        val DEFUALT_CALL_BACK: EngineCallBack = object :EngineCallBack{
            override fun onPreExecute(context: Context, params: Map<String, Any>?) {

            }

            override fun onError(e: Exception) {

            }

            override fun onSuccess(result: String) {

            }
        }
    }
}
