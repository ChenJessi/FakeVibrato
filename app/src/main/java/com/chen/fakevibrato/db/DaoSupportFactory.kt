package com.chen.fakevibrato.db

import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import java.io.File.separator

import java.io.File


class DaoSupportFactory private constructor(){

    companion object{
        val factory by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            DaoSupportFactory()
        }
    }
    private  var mSQLiteDatabase : SQLiteDatabase
    init {
        //把数据库放到存储卡里
        val dbRoot = File(Environment.getExternalStorageDirectory()
                .absolutePath + separator + "fakevibrato" + separator + "database")
        if (!dbRoot.exists()){
            dbRoot.mkdirs()
        }
        var dbFile = File(dbRoot,"fakevibrato.db")
        //打开或者创建一个数据库
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null)
    }


    fun <T> getDao(clazz : Class<T>) : IDaoSoupport<T>{
        var daoSoupport = DaoSupport<T>()
        daoSoupport.init(mSQLiteDatabase, clazz)
        return daoSoupport
    }



}