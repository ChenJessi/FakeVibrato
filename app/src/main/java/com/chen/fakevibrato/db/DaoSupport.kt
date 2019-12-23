package com.chen.fakevibrato.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.ArrayMap
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.lang.reflect.Method
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class DaoSupport<T> : IDaoSoupport<T> {
    private val TAG = "DaoSupport"
    private lateinit var mSqLiteDatabase: SQLiteDatabase
    private var mClazz : Class<T>? = null

    private val mPutMethodArgs = arrayOfNulls<Any>(2)
    private val mPutMethods = ArrayMap<String, Method>()


    override fun init(sqLiteDatabase: SQLiteDatabase, clazz: Class<T>) {
        this.mSqLiteDatabase = sqLiteDatabase
        this.mClazz = clazz

        // 创建表
        /*"create table if not exists Person ("
                + "id integer primary key autoincrement, "
                + "name text, "
                + "age integer, "
                + "flag boolean)";*/

        var sb = StringBuffer()
        sb.append("create table if not exists ")
                .append(DaoUtil.getTableName(clazz))
                .append("(id integer primary key autoincrement, )")

        var fields = mClazz?.declaredFields
        if (fields != null) {
            for (field in fields) {
                field.isAccessible = true
                var name = field.name
                var type = field.type.simpleName  //类型
                //  type需要进行转换 int --> integer, String text;
                sb.append(name).append(DaoUtil.getColumType(type)).append(", ")
            }
        }
        sb.replace(sb.length - 2, sb.length, ")")

        var createTableSql = sb.toString()

        Log.e(TAG, "createTableSql -> $createTableSql")

        mSqLiteDatabase.execSQL(createTableSql)

    }


    override fun insert(t: T): Long {
        /*ContentValues values = new ContentValues();
        values.put("name",person.getName());
        values.put("age",person.getAge());
        values.put("flag",person.getFlag());
        db.insert("Person",null,values);*/

        var values = contentValuesByObj(t)
        return mSqLiteDatabase.insert(DaoUtil.getTableName(mClazz), null , values)
    }

    override fun insert(datas: List<T>) {
        TODO() //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(): List<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * obj 转换 ContentValues
     */
    private fun contentValuesByObj(any: Any?) : ContentValues{
        var contentValues = ContentValues()

        //封装values
        var fields = mClazz?.declaredFields

        if (fields != null) {
            for (field in fields){
                try {//设置权限，私有和共有都可以访问
                    field.isAccessible = true
                    var key = field.name
                    //获取value
                    var value = field.get(any)
                    //put 第二个参数是类型 把它转换
                    mPutMethodArgs[0] = key
                    mPutMethodArgs[1] = value

                    var fieldTypeName = field.type.name
                    var putMethod = mPutMethods[fieldTypeName]
                    // 使用反射  获取方法  put  缓存方法
                    if (putMethod == null){
                        putMethod = ContentValues::class.java.getDeclaredMethod("put", String::class.java, value.javaClass)
                        mPutMethods[fieldTypeName] = putMethod
                    }
                    putMethod?.invoke(contentValues, mPutMethodArgs)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    mPutMethodArgs[0] = null
                    mPutMethodArgs[1] = null
                }
            }
        }
        return contentValues
    }

}