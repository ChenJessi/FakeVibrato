package com.chen.annotationutils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService



object AnnotatUtils {


    /**
     * 判断当前网络是否可用
     */
     fun networkAvailable(context: Context): Boolean {
        // 得到连接管理器对象
        try {
            val connectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo!!.isConnected) {
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }
}