package com.chen.fakevibrato.utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.chen.fakevibrato.R

/**
 * @author Created by CHEN on 2019/8/26
 * @email 188669@163.com
 */
object ToastUtils {

    fun showMessage(context: Context, message : String){
        var   view : View = LayoutInflater . from (context).inflate(R.layout.toast_message, null)
        var tvMessage : TextView = view.findViewById(R.id.tvMessage)
        tvMessage.text = message
        var toast = Toast(context)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.view = view
        toast.show()
    }
}