package com.chen.fakevibrato.http;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;
import android.util.Log;

import com.chen.fakevibrato.db.DaoUtil;
import com.chen.fakevibrato.db.IDaoSoupport;
import com.chen.fakevibrato.utils.MyLog;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/3/5.
 * Version 1.0
 * Description:
 */
public class DaoSupport<T> implements IDaoSoupport<T> {
    // SQLiteDatabase
    private SQLiteDatabase mSqLiteDatabase;
    // 泛型类
    private Class<T> mClazz;

    private String TAG = "DaoSupport";


    private static final Object[] mPutMethodArgs = new Object[2];

    private static final Map<String, Method> mPutMethods
            = new ArrayMap<>();

    public void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz) {
        this.mSqLiteDatabase = sqLiteDatabase;
        this.mClazz = clazz;

        // 创建表
        /*"create table if not exists Person ("
                + "id integer primary key autoincrement, "
                + "name text, "
                + "age integer, "
                + "flag boolean)";*/

        StringBuffer sb = new StringBuffer();

        sb.append("create table if not exists ")
                .append(DaoUtil.INSTANCE.getTableName(mClazz))
                .append("(id integer primary key autoincrement, ");

        Field[] fields = mClazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);// 设置权限
            String name = field.getName();
            String type = field.getType().getSimpleName();// int String boolean
            //  type需要进行转换 int --> integer, String text;
            sb.append(name).append(DaoUtil.INSTANCE.getColumType(type)).append(", ");
        }

        sb.replace(sb.length() - 2, sb.length(), ")");

        String createTableSql = sb.toString();

        Log.e(TAG, "表语句--> " + createTableSql);

        // 创建表
        mSqLiteDatabase.execSQL(createTableSql);
    }


    // 插入数据库 t 是任意对象
    @Override
    public long insert(T obj) {
        /*ContentValues values = new ContentValues();
        values.put("name",person.getName());
        values.put("age",person.getAge());
        values.put("flag",person.getFlag());
        db.insert("Person",null,values);*/


        // 使用的其实还是  原生的使用方式，只是我们是封装一下而已
        ContentValues values = contentValuesByObj(obj);

        // null  速度比第三方的快一倍左右
        return mSqLiteDatabase.insert(DaoUtil.INSTANCE.getTableName(mClazz), null, values);
    }
    @Override
    public void insert(@NotNull List<? extends T> datas) {
        // 批量插入采用 事物
        mSqLiteDatabase.beginTransaction();
        for (T data : datas) {
            // 调用单条插入
            insert(data);
        }
        mSqLiteDatabase.setTransactionSuccessful();
        mSqLiteDatabase.endTransaction();
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
    @Override
    public List<T> query() {
        Cursor cursor = mSqLiteDatabase.query(DaoUtil.INSTANCE.getTableName(mClazz),
                null, null, null, null, null, null);
        return cursorToList(cursor);
    }

    private List<T> cursorToList(Cursor cursor) {
        List<T> list = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            // 不断的从游标里面获取数据
            do {
                try {
                    // 通过反射new对象
                    T instance = mClazz.newInstance();
                    Field[] fields = mClazz.getDeclaredFields();


                    for (Field field : fields) {
                        // 遍历属性
                        field.setAccessible(true);
                        String name = field.getName();
                        // 获取角标  获取在第几列
                        int index = cursor.getColumnIndex(name);
                        if (index == -1) {
                            continue;
                        }

                        // 通过反射获取 游标的方法  field.getType() -> 获取的类型
                        Method cursorMethod = cursorMethod(field.getType());
                        if (cursorMethod != null) {
                            // 通过反射获取了 value
                            Object value = cursorMethod.invoke(cursor, index);
                            if (value == null) {
                                continue;
                            }

                            // 处理一些特殊的部分
                            if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                                if ("0".equals(String.valueOf(value))) {
                                    value = false;
                                } else if ("1".equals(String.valueOf(value))) {
                                    value = true;
                                }
                            } else if (field.getType() == char.class || field.getType() == Character.class) {
                                value = ((String) value).charAt(0);
                            } else if (field.getType() == Date.class) {
                                long date = (Long) value;
                                if (date <= 0) {
                                    value = null;
                                } else {
                                    value = new Date(date);
                                }
                            }

                            // 通过反射注入
                            field.set(instance, value);
                        }
                    }
                    // 加入集合
                    list.add(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // 获取游标的方法
    private Method cursorMethod(Class<?> type) throws Exception {
        String methodName = getColumnMethodName(type);
        // type String getString(index); int getInt; boolean getBoolean
        Method method = Cursor.class.getMethod(methodName, int.class);
        return method;
    }

    private String getColumnMethodName(Class<?> fieldType) {
        String typeName;
        if (fieldType.isPrimitive()) {
            typeName = DaoUtil.INSTANCE.capitalize(fieldType.getName());
        } else {
            typeName = fieldType.getSimpleName();
        }
        String methodName = "get" + typeName;
        if ("getBoolean".equals(methodName)) {
            methodName = "getInt";
        } else if ("getChar".equals(methodName) || "getCharacter".equals(methodName)) {
            methodName = "getString";
        } else if ("getDate".equals(methodName)) {
            methodName = "getLong";
        } else if ("getInteger".equals(methodName)) {
            methodName = "getInt";
        }
        return methodName;
    }

    // obj 转成 ContentValues
    private ContentValues contentValuesByObj(T obj) {
        // 第三方的 使用比对一下 了解一下源码
        ContentValues values = new ContentValues();
        // 封装values
        Field[] fields = mClazz.getDeclaredFields();
        MyLog.e("getTableName     fields   "+ fields.length);

        for (Field field : fields) {
            try {
                // 设置权限，私有和共有都可以访问
                field.setAccessible(true);
                String key = field.getName();
                // 获取value
                Object value = field.get(obj);
                // put 第二个参数是类型  把它转换

                mPutMethodArgs[0] = key;
                mPutMethodArgs[1] = value;

                // 方法使用反射 ， 反射在一定程度上会影响性能
                // 源码里面  activity实例 反射  View创建反射
                // 第三方以及是源码给我们提供的是最好的学习教材   插件换肤
                // 感谢google提供的源码，我们明天再见

                String filedTypeName = field.getType().getName();
                // 还是使用反射  获取方法  put  缓存方法
                Method putMethod = mPutMethods.get(filedTypeName);
                if (putMethod == null) {
                    putMethod = ContentValues.class.getDeclaredMethod("put",
                            String.class, value.getClass());
                    mPutMethods.put(filedTypeName, putMethod);
                }

                MyLog.e("getTableName     putMethod  " + values.size()+"    " +mPutMethodArgs.toString());
                // 通过反射执行
                putMethod.invoke(values, mPutMethodArgs);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mPutMethodArgs[0] = null;
                mPutMethodArgs[1] = null;
            }
        }
        MyLog.e("getTableName     contentValues  "+values.size());
        return values;
    }

    /**
     * 删除
     */
    public int delete(String whereClause, String[] whereArgs) {
        return mSqLiteDatabase.delete(DaoUtil.INSTANCE.getTableName(mClazz), whereClause, whereArgs);
    }

    /**
     * 更新  这些你需要对  最原始的写法比较明了 extends
     */
    public int update(T obj, String whereClause, String... whereArgs) {
        ContentValues values = contentValuesByObj(obj);
        return mSqLiteDatabase.update(DaoUtil.INSTANCE.getTableName(mClazz),
                values, whereClause, whereArgs);
    }



    // 结合到
    // 1. 网络引擎的缓存
    // 2. 资源加载的源码NDK
}
