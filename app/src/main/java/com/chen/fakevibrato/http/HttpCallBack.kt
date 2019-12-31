package com.chen.fakevibrato.http

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.gson.Gson
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




abstract class HttpCallBack<T> : EngineCallBack {
    override fun onPreExecute(context: Context, params: MutableMap<String, Any>?) {

        // 项目名称  context
        if (params != null) {
            params["app_name"] = "fakeVibrato"
            params["version_name"] = "5.7.0"
            params["ac"] = "wifi"
            params["device_id"] = "30036118478"
            params["device_brand"] = "Xiaomi"
            params["update_version_code"] = "5701"
            params["manifest_version_code"] = "570"
            params["longitude"] = "113.000366"
            params["latitude"] = "28.171377"
            params["device_platform"] = "android"
        }
    }
    // 开始执行了
    fun onPreExecute() {

    }
    override fun onError(e: Exception) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(result: String) {

        val gson = Gson()
        // data:{"name","darren"}   data:"请求失败"
        val objResult = gson.fromJson(result,
                analysisClazzInfo(this)) as T
        onSuccess(objResult)
    }
    // 返回可以直接操作的对象
    abstract fun onSuccess(result: T)
}