package com.chen.fakevibrato.http

import android.content.Context

 interface IHttpEngine {
    fun get(context: Context, cache : Boolean , url :String , params : Map<String, Any>?, callBack: EngineCallBack)
    fun post(context: Context, cache : Boolean , url :String , params : Map<String, Any>?, callBack: EngineCallBack)

}