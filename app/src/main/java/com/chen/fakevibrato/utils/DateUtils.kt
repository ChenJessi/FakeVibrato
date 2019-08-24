package com.chen.fakevibrato.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Created by CHEN on 2019/8/24
 * @email 188669@163.com
 */
object DateUtils {

    fun dateToYMD(date: Date): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(date)
    }
}