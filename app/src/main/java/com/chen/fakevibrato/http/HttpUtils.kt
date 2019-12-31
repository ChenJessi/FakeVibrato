package com.chen.fakevibrato.http

import android.content.Context
import android.util.Log
import com.chen.fakevibrato.utils.MyLog
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType
import io.rong.imkit.utils.StringUtils.getKey
import java.lang.reflect.ParameterizedType



class HttpUtils private constructor(var mContext: Context) {
    private val GET_TYPE = 0x0022
    private val POST_TYPE = 0x0011
    private var url : String = ""
    //请求方式
    private var type = GET_TYPE

    private var mCache = false
    private var mParams : MutableMap<String, Any>? = null

    init {
        mParams = HashMap()
    }

    fun url(url : String) : HttpUtils{
        this.url = url
        return this
    }

    fun post() : HttpUtils {
        this.type = POST_TYPE
        return this
    }

    fun get() : HttpUtils {
        this.type = GET_TYPE
        return this
    }

    fun cache(isCache : Boolean ): HttpUtils {
        mCache = isCache
        return this
    }

    //添加参数
    fun addParam(key : String, value : Any) : HttpUtils{
        mParams?.put(key, value)
        return this
    }
    fun addParams(params : MutableMap<String, Any>) : HttpUtils{
        mParams?.putAll(params)
        return this
    }


    //请求头

    //添加回调 执行
     fun execute(callBack: EngineCallBack){
        //每次执行都会进入这个方法
        var callBack = callBack
        callBack.onPreExecute(mContext, mParams)
        //判断执行方法
        if (type == POST_TYPE){
            post(url, mParams, callBack)
        }
        if (type == GET_TYPE){
            get(url, mParams, callBack)
        }

    }
    fun execute(){
        execute(EngineCallBack.DEFUALT_CALL_BACK)
    }

    companion object{
        fun with(context: Context): HttpUtils {
            return HttpUtils(context)
        }

        //默认的OkHttpEngine
         var mHttpEngine : IHttpEngine = OkHttpEngine()

        // application初始化引擎
        fun  init(httpEngine: IHttpEngine) {
            mHttpEngine = httpEngine
        }


    }


    /**
     * 每次可以自带引擎
     */
    fun exchangeEngine(httpEngine: IHttpEngine) : HttpUtils {
        mHttpEngine = httpEngine
        return this
    }

    private fun get(url: String, mParams: Map<String, Any>?, callBack: EngineCallBack) {
        mHttpEngine.get(mContext,mCache,url, mParams, callBack)
    }

    private fun post(url: String, mParams: MutableMap<String, Any>?, callBack: EngineCallBack) {
        mHttpEngine.post(mContext,mCache, url, mParams, callBack)
    }


}

/**
 * 解析一个类上面的class信息
 */
fun analysisClazzInfo(any : Any) : Class<*>{
    var genType = any.javaClass.genericSuperclass
    var params = (genType as ParameterizedType ).actualTypeArguments
    return params[0] as Class<*>
}
/**
 * 拼接参数
 */
fun jointParams(url : String, params : Map<String, Any>?) : String{

    if (params == null || params.isEmpty()) {
        return url
    }
    val stringBuffer = StringBuffer(url)
    if (!url.contains("?")) {
        stringBuffer.append("?")
    } else {
        if (!url.endsWith("?")) {
            stringBuffer.append("&")
        }
    }

    for (entry in params.entries) {
        stringBuffer.append(entry.key + "=" + entry.value + "&")
    }
    stringBuffer.deleteCharAt(stringBuffer.length - 1)

    return stringBuffer.toString()
}

