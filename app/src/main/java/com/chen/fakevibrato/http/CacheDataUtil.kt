package com.chen.fakevibrato.http

import android.util.Log
import com.chen.fakevibrato.db.DaoSupportFactory
import java.nio.file.Files.delete


/**
 * 缓存类
 */
object CacheDataUtil {

    fun getCacheResultJson(url : String) : String {
        var dataDaoSupport = DaoSupportFactory.factory.getDao(CacheData::class.java)
        // 需要缓存，从数据库拿缓存，问题又来了，OkHttpEngine  BaseLibrary
        // 数据库缓存在 FrameLibrary
        //此处对url加密 保存和查询
        var cacheDatas = dataDaoSupport.querySupport().selection("mUrlKey = ?").selectionArgs(url).query()
        if (cacheDatas.isNotEmpty()){
            //有数据
            var cacheData = cacheDatas[0]
            var resultJson = cacheData.resultJson
            return resultJson
        }
        return toString()
    }

    /**
     * 缓存数据
     */
    fun cacheData(finalUrl : String, resultJson : String) : Long {
        val dataDaoSupport = DaoSupportFactory.factory.getDao(CacheData::class.java)
        dataDaoSupport.delete("mUrlKey=?", finalUrl)
        val number = dataDaoSupport.insert(CacheData(finalUrl, resultJson))
        Log.e("TAG", "number --> $number")
        return number
    }
}