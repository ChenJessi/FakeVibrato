package com.chen.fakevibrato.db

import android.database.sqlite.SQLiteDatabase
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



interface IDaoSoupport<T> {
    fun init(sqLiteDatabase: SQLiteDatabase, clazz : Class<T>)
    //插入数据
    fun insert(t : T) : Long
    //批量插入
    fun insert(datas : List<T>)

    //查询所有
    fun query() : List<T>

    //获取专门查询的支持类
    fun querySupport() : QuerySupport<T>

    fun delete(whereClause: String, vararg whereArgs: String): Int

    fun update(obj: T, whereClause: String, vararg whereArgs: String): Int
}