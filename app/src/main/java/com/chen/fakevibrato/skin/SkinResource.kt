package com.chen.fakevibrato.skin

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Environment
import android.util.Log
import java.io.File


/**
 * 皮肤资源管理
 */
class SkinResource constructor(var mContext : Context, var skinPath : String){
    private val TAG = this.javaClass.simpleName
    private var mSkinResource: Resources? = null
    private lateinit var mPackageName : String

    init {
        try {//读取本地     .skin 里的资源
            var superRes = mContext.resources
            // 创建 assetManager
            var asset = AssetManager::class.java.newInstance()

            //添加本地下载好的资源皮肤
            var method = AssetManager::class.java.getDeclaredMethod("addAssetPath", String::class.java)
            //.method.setAccessible(true)  如果是私有添加权限
            //反射执行方法
            method.invoke(asset, Environment.getExternalStorageDirectory().absolutePath + "" + File.separator + "red.skin")

            mSkinResource = Resources(asset, superRes.displayMetrics, superRes.configuration)

            // 获取skinPath包名
            mPackageName = mContext.packageManager.getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES).packageName
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 通过名字获取drawable
     */
     fun getDrawableByName(resName : String) : Drawable? {
        try {
            var resId = mSkinResource?.getIdentifier(resName, "drawable", mPackageName)
            Log.e(TAG, "resId -> $resId mPackageName -> $mPackageName resName -> $resName")
            val drawable = resId?.let { mSkinResource?.getDrawable(it) }
            return drawable
        } catch (e: Exception) {
            return null
        }
    }


    /**
     * 通过名字获取颜色
     */
    fun getColorByName(resName: String): ColorStateList? = try {
        val resId = mSkinResource?.getIdentifier(resName, "color", mPackageName)
        resId?.let { mSkinResource?.getColorStateList(it) }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

}