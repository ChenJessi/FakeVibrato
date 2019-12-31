package com.chen.fakevibrato.http

import android.content.Context
import android.util.Log
import okhttp3.*
import java.io.File
import java.io.IOException
import java.net.URLConnection
import com.chen.fakevibrato.http.IHttpEngine
import org.json.JSONObject

class OkHttpEngine : IHttpEngine {

    private val mOkHttpClient = OkHttpClient()


    override fun get(context: Context, cache : Boolean , url: String, params: Map<String, Any>?, callBack: EngineCallBack) {
        var url = jointParams(url, params)
        Log.e("Get请求路径 ： ", url)

        var requestBuilder = Request.Builder().url(url).tag(context)
        var request = requestBuilder.build()

        mOkHttpClient.run {

            newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    callBack.onError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    var resultJson = response.body?.string()
                    if (resultJson != null) {
                        callBack.onSuccess(resultJson)
                    }
                    Log.e("Get返回结果：", resultJson)
                    var jsonObject :JSONObject = JSONObject(resultJson)
                    Log.e("Get返回结果：", jsonObject.toString())
                }
            })
        }
    }

    override fun post(context: Context, cache : Boolean ,url: String, params: Map<String, Any>?, callBack: EngineCallBack) {
        var jointUrl = jointParams(url, params)
        Log.e("Post请求路径： ", jointUrl)

        var requestBody = appendBody(params)
        var request = Request.Builder()
                .url(url)
                .tag(context)
                .post(requestBody)
                .build()
        mOkHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                callBack.onError(e)
            }

            override fun onResponse(call: Call, response: Response) {
                var result = request.body.toString()
                Log.e("Post返回结果 ： ", jointUrl)
                callBack.onSuccess(result)
            }
        })
    }


    /**
     *组装post请求桉树body
     */
    protected fun appendBody(params: Map<String, Any>?): RequestBody {
        var builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        return builder.build()
    }

    // 添加参数
    private fun addParams(builder: MultipartBody.Builder, params: Map<String, Any>?) {
        if (params != null && params.isNotEmpty()) {
            for (key in params.keys) {
                builder.addFormDataPart(key, params[key].toString())
                var value = params[key]
                when (value) {
                    is File -> {
                        var file: File = value

                        builder.addFormDataPart(key, file.name, RequestBody.run {
                            file.asRequestBody(MediaType.run {
                                guessMimeType(file.absolutePath).toMediaType()
                            })
                        })
                    }
                    is List<*> -> {
                        try {
                            var listFiles  = value  as List<File>
                            for (i in listFiles.indices){
                                var file = listFiles[i]
                                builder.addFormDataPart(key + i, file.name, RequestBody.run {
                                    file.asRequestBody(MediaType.run {
                                        guessMimeType(file.absolutePath).toMediaType()
                                    })
                                })
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    else -> {
                        builder.addFormDataPart(key, value.toString())
                    }
                }
            }
        }
    }


    /**
     * 猜测文件类型
     */
    private fun guessMimeType(path: String): String {
        var fileNameMap = URLConnection.getFileNameMap()
        var contentTypeFor = fileNameMap.getContentTypeFor(path)
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream"
        }
        return contentTypeFor
    }
}