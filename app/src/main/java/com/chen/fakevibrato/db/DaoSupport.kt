package com.chen.fakevibrato.db

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.text.TextUtils
import android.util.ArrayMap
import android.util.Log
import com.chen.fakevibrato.utils.MyLog
import java.lang.reflect.Method
import java.util.*


class DaoSupport<T> : IDaoSoupport<T> {

    private val TAG = "DaoSupport"
    private lateinit var mSqLiteDatabase: SQLiteDatabase
    private var mClazz: Class<T>? = null

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


    override fun insert(t: T): Long {
        /*ContentValues values = new ContentValues();
      values.put("name",person.getName());
      values.put("age",person.getAge());
      values.put("flag",person.getFlag());
      db.insert("Person",null,values);*/


        // 使用的其实还是  原生的使用方式，只是我们是封装一下而已
        val values = contentValuesByObj(t)

        // null  速度比第三方的快一倍左右
        return mSqLiteDatabase!!.insert(DaoUtil.getTableName(mClazz), null, values)
    }

    override fun insert(datas: List<T>) {
        mSqLiteDatabase.beginTransaction()

        for (data in datas) {
            insert(data)
        }

        mSqLiteDatabase.setTransactionSuccessful()
        mSqLiteDatabase.endTransaction()
    }

    /**
     * /查询目前直接查询所有,希望单独写一个类做到按条件查询:age = 22  name = darren
     */
    override fun query(): List<T> {
        var cursor = mSqLiteDatabase.query(DaoUtil.getTableName(mClazz), null, null, null, null, null, null)
        return cursorToList(cursor)
    }

    /**
     *
     */
    private fun cursorToList(cursor: Cursor?): List<T> {
        var list = ArrayList<T>()
        if (cursor != null && cursor.moveToFirst()) {
            do {

                try {//从游标获取数据
                    //通过反射new对象
                    var instance = mClazz?.newInstance()
                    var fields = mClazz?.declaredFields

                    //遍历属性
                    if (fields != null) {
                        for (field in fields) {
                            field.isAccessible = true
                            var name = field.name
                            //获取角标  获取在第几列
                            var index = cursor.getColumnIndex(name)
                            if (index == -1) {
                                continue
                            }

                            // 通过反射获取 游标的方法  field.getType() -> 获取的类型
                            var cursorMethod = cursorMethod(field.type)
                            var value: Any? = cursorMethod.invoke(cursor, index) ?: continue

                            //处理一些特殊的部分
                            if (field.type === Boolean::class.javaPrimitiveType || field.type === Boolean::class.java) {
                                if (TextUtils.equals("0", value.toString())) {
                                    value = false
                                } else if (TextUtils.equals("1", value.toString())) {
                                    value = true
                                }
                            }
                            //通过反射注入
                            field.set(instance, value)
                        }
                    }
                    instance?.let { list.add(it) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } while (cursor.moveToNext())
        }
        cursor?.close()
        return list
    }

    /**
     * 获取游标的方法
     */
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

    /**
     * obj 转换 ContentValues
     */
    private fun contentValuesByObj(any: T): ContentValues {
        // 第三方的 使用比对一下 了解一下源码
        val contentValues = ContentValues()
        // 封装values
        val fields = mClazz!!.declaredFields
        //封装values
        for (field in fields) {
            try {//设置权限，私有和共有都可以访问
                field.isAccessible = true
                val key = field.name
                // 获取value
                val value = field.get(any)
                // put 第二个参数是类型  把它转换

                mPutMethodArgs[0] = key
                mPutMethodArgs[1] = value

                val filedTypeName = field.type.name
                // 还是使用反射  获取方法  put  缓存方法
                var putMethod = mPutMethods[filedTypeName]
                if (putMethod == null) {
                    putMethod = ContentValues::class.java.getDeclaredMethod("put",
                            String::class.java, value!!.javaClass)
                    mPutMethods[filedTypeName] = putMethod
                }
                // 通过反射执行
                putMethod!!.invoke(contentValues, *mPutMethodArgs)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                mPutMethodArgs[0] = null
                mPutMethodArgs[1] = null
            }
        }

        return contentValues
    }
}