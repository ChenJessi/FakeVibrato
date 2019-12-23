package com.chen.fakevibrato.db

import android.text.TextUtils
import java.util.*
import kotlin.contracts.contract

object DaoUtil {

    fun getTableName(clazz: Class<*>?) : String{
        return clazz?.simpleName.toString()
    }

    fun getColumType(type : String) : String {
        var value : String? = null
        when {
            type.contains("String") -> value = " text"
            type.contains("int") -> value = " integer"
            type.contains("boolean") -> value = " boolean"
            type.contains("float") -> value = " float"
            type.contains("double") -> value = " double"
            type.contains("varchar") -> value = " char"
            type.contains("long") -> value = " long"
            else -> value = "未知"
        }
        return value
    }

    fun capitalize(string: String) : String? {
        if (!TextUtils.isEmpty(string)) {
            return string.substring(0, 1).toUpperCase(Locale.US) + string.substring(1)
        }
        return if (string == null) null else ""
    }
}