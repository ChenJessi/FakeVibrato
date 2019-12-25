package com.chen.fakevibrato.db

import android.database.sqlite.SQLiteDatabase

/**
 * 查询支持类
 */
class QuerySupport<T>(var sqLiteDatabase: SQLiteDatabase, var clazz: Class<T>) {
    //查询的列
    private var mQueryColumns: Array<out String>? = null
    //查询的条件
    private var mQuerySelection : String ? = null
    //查询的参数
    private var mQuerySelectionArgs : Array<out String>? = null
    //查询对结果集进行过滤
    private var mQueryHaving : String? = null

    fun columns(vararg columns: String): QuerySupport<T> {
        this.mQueryColumns = columns
        return this
    }

    fun selectionArgs(vararg selectionArgs : String) : QuerySupport<T>{
        this.mQuerySelectionArgs = selectionArgs
        return this
    }

    fun having(having : String) : QuerySupport<T>{
        this.mQueryHaving = having
        return this
    }
}