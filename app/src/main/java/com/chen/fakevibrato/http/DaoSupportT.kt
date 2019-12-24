package com.chen.fakevibrato.http

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.ArrayMap
import android.util.Log

import com.chen.fakevibrato.db.DaoUtil
import com.chen.fakevibrato.db.IDaoSoupport
import com.chen.fakevibrato.utils.MyLog

import java.lang.reflect.Method
import java.util.ArrayList
import java.util.Date

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/3/5.
 * Version 1.0
 * Description:
 */
class DaoSupportT<T> : IDaoSoupport<T> {
    // SQLiteDatabase
    private var mSqLiteDatabase: SQLiteDatabase? = null
    // 泛型类
    private var mClazz: Class<T>? = null

    private val TAG = "DaoSupport"

    override fun init(sqLiteDatabase: SQLiteDatabase, clazz: Class<T>) {
        this.mSqLiteDatabase = sqLiteDatabase
        this.mClazz = clazz

        // 创建表
        /*"create table if not exists Person ("
                + "id integer primary key autoincrement, "
                + "name text, "
                + "age integer, "
                + "flag boolean)";*/

        val sb = StringBuffer()

        sb.append("create table if not exists ")
                .append(DaoUtil.getTableName(mClazz))
                .append("(id integer primary key autoincrement, ")

        val fields = mClazz!!.declaredFields
        for (field in fields) {
            field.isAccessible = true// 设置权限
            val name = field.name
            val type = field.type.simpleName// int String boolean
            //  type需要进行转换 int --> integer, String text;
            sb.append(name).append(DaoUtil.getColumType(type)).append(", ")
        }

        sb.replace(sb.length - 2, sb.length, ")")

        val createTableSql = sb.toString()

        Log.e(TAG, "表语句--> $createTableSql")

        // 创建表
        mSqLiteDatabase!!.execSQL(createTableSql)
    }


    // 插入数据库 t 是任意对象
    override fun insert(obj: T): Long {
        /*ContentValues values = new ContentValues();
        values.put("name",person.getName());
        values.put("age",person.getAge());
        values.put("flag",person.getFlag());
        db.insert("Person",null,values);*/


        // 使用的其实还是  原生的使用方式，只是我们是封装一下而已
        val values = contentValuesByObj(obj)

        // null  速度比第三方的快一倍左右
        return mSqLiteDatabase!!.insert(DaoUtil.getTableName(mClazz), null, values)
    }

    override fun insert(datas: List<T>) {
        // 批量插入采用 事物
        mSqLiteDatabase!!.beginTransaction()
        for (data in datas) {
            // 调用单条插入
            insert(data)
        }
        mSqLiteDatabase!!.setTransactionSuccessful()
        mSqLiteDatabase!!.endTransaction()
    }
    //    @Override
    //    public void insert(List<T> datas) {
    //        // 批量插入采用 事物
    //        mSqLiteDatabase.beginTransaction();
    //        for (T data : datas) {
    //            // 调用单条插入
    //            insert(data);
    //        }
    //        mSqLiteDatabase.setTransactionSuccessful();
    //        mSqLiteDatabase.endTransaction();
    //    }

    // 查询目前直接查询所有,希望单独写一个类做到按条件查询:age = 22  name = darren
    override fun query(): List<T> {
        val cursor = mSqLiteDatabase!!.query(DaoUtil.getTableName(mClazz), null, null, null, null, null, null)
        return cursorToList(cursor)
    }

    private fun cursorToList(cursor: Cursor?): List<T> {
        val list = ArrayList<T>()
        if (cursor != null && cursor.moveToFirst()) {
            // 不断的从游标里面获取数据
            do {
                try {
                    // 通过反射new对象
                    val instance = mClazz!!.newInstance()
                    val fields = mClazz!!.declaredFields


                    for (field in fields) {
                        // 遍历属性
                        field.isAccessible = true
                        val name = field.name
                        // 获取角标  获取在第几列
                        val index = cursor.getColumnIndex(name)
                        if (index == -1) {
                            continue
                        }

                        // 通过反射获取 游标的方法  field.getType() -> 获取的类型
                        val cursorMethod = cursorMethod(field.type)
                        if (cursorMethod != null) {
                            // 通过反射获取了 value
                            var value: Any? = cursorMethod.invoke(cursor, index) ?: continue

                            // 处理一些特殊的部分
                            if (field.type == Boolean::class.javaPrimitiveType || field.type == Boolean::class.java) {
                                if ("0" == value.toString()) {
                                    value = false
                                } else if ("1" == value.toString()) {
                                    value = true
                                }
                            } else if (field.type == Char::class.javaPrimitiveType || field.type == Char::class.java) {
                                value = (value as String)[0]
                            } else if (field.type == Date::class.java) {
                                val date = value as Long
                                if (date <= 0) {
                                    value = null
                                } else {
                                    value = Date(date)
                                }
                            }

                            // 通过反射注入
                            field.set(instance, value)
                        }
                    }
                    // 加入集合
                    list.add(instance)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } while (cursor.moveToNext())
        }
        cursor!!.close()
        return list
    }

    // 获取游标的方法
    @Throws(Exception::class)
    private fun cursorMethod(type: Class<*>): Method {
        val methodName = getColumnMethodName(type)
        // type String getString(index); int getInt; boolean getBoolean
        return Cursor::class.java.getMethod(methodName, Int::class.javaPrimitiveType)
    }

    private fun getColumnMethodName(fieldType: Class<*>): String {
        val typeName: String
        if (fieldType.isPrimitive) {
            typeName = DaoUtil.capitalize(fieldType.name)
        } else {
            typeName = fieldType.simpleName
        }
        var methodName = "get$typeName"
        if ("getBoolean" == methodName) {
            methodName = "getInt"
        } else if ("getChar" == methodName || "getCharacter" == methodName) {
            methodName = "getString"
        } else if ("getDate" == methodName) {
            methodName = "getLong"
        } else if ("getInteger" == methodName) {
            methodName = "getInt"
        }
        return methodName
    }

    // obj 转成 ContentValues
    private fun contentValuesByObj(obj: T): ContentValues {
        // 第三方的 使用比对一下 了解一下源码
        val values = ContentValues()
        // 封装values
        val fields = mClazz!!.declaredFields
        MyLog.e("getTableName     fields   " + fields.size)

        for (field in fields) {
            try {
                // 设置权限，私有和共有都可以访问
                field.isAccessible = true
                val key = field.name
                // 获取value
                val value = field.get(obj)
                // put 第二个参数是类型  把它转换

                mPutMethodArgs[0] = key
                mPutMethodArgs[1] = value

                // 方法使用反射 ， 反射在一定程度上会影响性能
                // 源码里面  activity实例 反射  View创建反射
                // 第三方以及是源码给我们提供的是最好的学习教材   插件换肤
                // 感谢google提供的源码，我们明天再见

                val filedTypeName = field.type.name
                // 还是使用反射  获取方法  put  缓存方法
                var putMethod = mPutMethods[filedTypeName]
                if (putMethod == null) {
                    putMethod = ContentValues::class.java.getDeclaredMethod("put",
                            String::class.java, value!!.javaClass)
                    mPutMethods[filedTypeName] = putMethod
                }

                MyLog.e("getTableName     putMethod  " + values.size() + "    " + mPutMethodArgs.toString())
                // 通过反射执行
                putMethod!!.invoke(values, *mPutMethodArgs)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                mPutMethodArgs[0] = null
                mPutMethodArgs[1] = null
            }
        }
        MyLog.e("getTableName     contentValues  " + values.size())
        return values
    }

    /**
     * 删除
     */
    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return mSqLiteDatabase!!.delete(DaoUtil.getTableName(mClazz), whereClause, whereArgs)
    }

    /**
     * 更新  这些你需要对  最原始的写法比较明了 extends
     */
    fun update(obj: T, whereClause: String, vararg whereArgs: String): Int {
        val values = contentValuesByObj(obj)
        return mSqLiteDatabase!!.update(DaoUtil.getTableName(mClazz),
                values, whereClause, whereArgs)
    }

    companion object {


        private val mPutMethodArgs = arrayOfNulls<Any>(2)

        private val mPutMethods = ArrayMap<String, Method>()
    }


    // 结合到
    // 1. 网络引擎的缓存
    // 2. 资源加载的源码NDK
}
