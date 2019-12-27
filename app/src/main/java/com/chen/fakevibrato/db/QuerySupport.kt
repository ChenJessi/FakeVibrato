package com.chen.fakevibrato.db

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.text.TextUtils
import java.lang.reflect.Method
import java.util.*
import kotlin.collections.ArrayList


/**
 * 查询支持类
 */
class QuerySupport<T>(var mSQLiteDatabase: SQLiteDatabase, var clazz: Class<T>) {
    //查询的列
    private var mQueryColumns: Array<out String>? = null
    //查询的条件
    private var mQuerySelection: String? = null
    //查询的参数
    private var mQuerySelectionArgs: Array<out String>? = null
    //查询对结果集进行过滤
    private var mQueryHaving: String? = null

    //查询排序
    private var mQueryOrderBy: String? = null

    //查询分页
    private var mQueryLimit: String? = null

    //查询分组
    private var mQueryGroupBy: String? = null

    private var mClass: Class<T>? = null

    fun columns(vararg columns: String): QuerySupport<T> {
        this.mQueryColumns = columns
        return this
    }

    fun selectionArgs(vararg selectionArgs: String): QuerySupport<T> {
        this.mQuerySelectionArgs = selectionArgs
        return this
    }

    fun having(having: String): QuerySupport<T> {
        this.mQueryHaving = having
        return this
    }

    fun orderBy(orderBy: String): QuerySupport<T> {
        this.mQueryOrderBy = orderBy
        return this
    }

    fun limit(limit: String): QuerySupport<T> {
        this.mQueryLimit = limit
        return this
    }

    fun groupBy(groupBy: String): QuerySupport<T> {
        this.mQueryGroupBy = groupBy
        return this
    }

    fun selection(selection: String): QuerySupport<T> {
        this.mQuerySelection = selection
        return this
    }

    fun query(): List<T> {
        var cursor = mSQLiteDatabase.query(DaoUtil.getTableName(mClass), mQueryColumns, mQuerySelection,
                mQuerySelectionArgs, mQueryGroupBy, mQueryHaving, mQueryOrderBy, mQueryLimit)
        clearQueryParams()
        return cursorToList(cursor)
    }

    private fun clearQueryParams() {
        mQueryColumns = null
        mQuerySelection = null
        mQuerySelectionArgs = null
        mQueryGroupBy = null
        mQueryHaving = null
        mQueryOrderBy = null
        mQueryLimit = null
    }

    /**
     * 通过Cursor封装成查找对象
     * @return 对象集合列表
     */
    private fun cursorToList(cursor: Cursor): List<T> {
        val list = ArrayList<T>()
        if (cursor.moveToFirst()) {
            do {
                try {
                    var instance = mClass!!.newInstance()
                    var fields = mClass!!.declaredFields
                    for (field in fields) {
                        //遍历属性
                        field.isAccessible = true
                        var name = field.name
                        //获取角标
                        var index = cursor.getColumnIndex(name)
                        if (index == -1) {
                            continue
                        }
                        //通过反射获取 游标
                        var cursorMethod = cursorMethod(field.type)
                        var value: Any? = cursorMethod.invoke(cursor, index) ?: continue

                        //处理一些特殊的部分
                        if (field.type === Boolean::class.javaPrimitiveType || field.type === Boolean::class.java) {
                            if (TextUtils.equals("0", value.toString())) {
                                value = false
                            } else if (TextUtils.equals("1", value.toString())) {
                                value = true
                            }
                        } else if (field.type === Char::class.javaPrimitiveType || field.type === Char::class.java) {
                            value = (value as String)[0]
                        } else if (field.type === Date::class.java) {
                            val date = value as Long
                            if (date <= 0) {
                                value = null
                            } else {
                                value = Date(date)
                            }
                        }
                        field.set(instance, value)
                    }
                    list.add(instance)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    @Throws(Exception::class)
    private fun cursorMethod(type: Class<*>): Method {
        var methodName = getColumnMethodName(type)
        var method = Cursor::class.java.getMethod(methodName, Int::class.javaPrimitiveType)
        return method
    }

    private fun getColumnMethodName(fieldType: Class<*>): String {
        var typeName: String
        if (fieldType.isPrimitive) {
            typeName = DaoUtil.capitalize(fieldType.name)
        } else {
            typeName = fieldType.simpleName
        }

        var methodName = "get$typeName"
        if (TextUtils.equals("getBoolean", methodName)) {
            methodName = "getInt"
        } else if (TextUtils.equals("getChar", methodName) || TextUtils.equals("getCharacter", methodName)) {
            methodName = "getString"
        } else if (TextUtils.equals("getDate", methodName)) {
            methodName = "getLong"
        } else if (TextUtils.equals("getInteger", methodName)) {
            methodName = "getInt"
        }
        return methodName
    }
}