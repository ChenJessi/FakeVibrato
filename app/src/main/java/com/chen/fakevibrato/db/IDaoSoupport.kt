package com.chen.fakevibrato.db

import android.database.sqlite.SQLiteDatabase

interface IDaoSoupport<T> {
    fun init(sqLiteDatabase: SQLiteDatabase, clazz : Class<T>)
    //插入数据
    fun insert(t : T) : Long
    //批量插入
    fun insert(datas : List<T>)

    //查询所有
    fun query() : List<T>


}