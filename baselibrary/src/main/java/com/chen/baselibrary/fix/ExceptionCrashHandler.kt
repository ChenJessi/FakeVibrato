package com.chen.baselibrary.fix

import android.content.Context
import android.util.Log
import java.io.File
import android.os.Build.PRODUCT
import android.os.Build.VERSION.SDK_INT
import android.R.attr.versionCode
import android.R.attr.versionName
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.GET_ACTIVITIES
import android.os.Build
import android.os.Environment
import java.io.PrintWriter
import java.io.StringWriter
import android.os.Environment.MEDIA_MOUNTED
import java.io.FileOutputStream
import java.text.SimpleDateFormat


/**
 *异常捕捉
 */
class ExceptionCrashHandler : Thread.UncaughtExceptionHandler {
    private val TAG = "ExceptionCrashHandler"
    // 获取系统默认的
    private var mDefaultExceptionHandler: Thread.UncaughtExceptionHandler? = null
    // 用来获取应用的一些信息
    private var mContext: Context? = null

    companion object{
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            ExceptionCrashHandler()
        }
    }

     fun init (context: Context){
        this.mContext = context
         //设置全局的异常类为本类
        Thread.currentThread().uncaughtExceptionHandler = this
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
    }



    override fun uncaughtException(t: Thread, e: Throwable) {
        //全局异常
        Log.e(TAG, "报异常了~")
        //写入到本地文件  ex 当前的版本 手机信息

        //1. 崩溃的异常信息

        // 2. 应用信息  包名 版本号

        //3. 手机信息

        //4. 保存当前文件，等应用再次启动上传

        var crashFileName = saveInfoToSD(e)

        Log.e(TAG, "fileName --> $crashFileName")

        //3. 缓存崩溃日志文件
        cacheCrashFile(crashFileName)

        //让系统默认处理
        mDefaultExceptionHandler?.uncaughtException(t, e)
    }

    /**
     *
     * 缓存崩溃日志文件
     */
    private fun cacheCrashFile(fileName : String){
        var sp = mContext?.getSharedPreferences("crash", Context.MODE_PRIVATE)
        sp?.edit()?.putString("CRASH_FILE_NAME", fileName)?.commit()
    }

    /**
     * 获取崩溃文件名称
     */
    fun getCrashFile() : File {
        var crashFileName = mContext?.getSharedPreferences("crash",
                Context.MODE_PRIVATE)?.getString("CRASH_FILE_NAME", "")
        return  File(crashFileName)
    }

    /**
     * 获取保存的软件信息，设备信息和出错信息保存在SDcard中
     */
    private fun saveInfoToSD(e : Throwable) : String{
        var fileName : String? = null
        var sb = StringBuffer()
        // 1. 手机信息 + 应用信息   --> obtainSimpleInfo()
        var a = java.util.HashMap<String, String>()

        var set = mContext?.let { obtainSimpleInfo(it).entries }
        if (set != null) {
            for (entry in set) {
                val key = entry.key
                val value = entry.value
                sb.append(key).append(" = ").append(value).append("\n")
            }
        }

        // 2.崩溃的详细信息
        sb.append(obtainExceptionInfo(e))

        // 保存文件  手机应用的目录，并没有拿手机sdCard目录， 6.0 以上需要动态申请权限


        if (Environment.getExternalStorageState().equals(
                        MEDIA_MOUNTED)) {

            val dir = File(mContext?.filesDir.toString() + File.separator + "crash"
                    + File.separator)

            // 先删除之前的异常信息
            if (dir.exists()) {
                // 删除该目录下的所有子文件
                deleteDir(dir)
            }

            // 再从新创建文件夹
            if (!dir.exists()) {
                dir.mkdir()
            }

            try {
                fileName = (dir.toString() + File.separator + getAssignTime("yyyy_MM_dd_HH_mm") + ".txt")
                val fos = FileOutputStream(fileName)
                fos.write(sb.toString().toByteArray())
                fos.flush()
                fos.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return fileName.toString()
    }

    private fun getAssignTime(dateFormatStr: String): String {
        val dataFormat = SimpleDateFormat(dateFormatStr)
        val currentTime = System.currentTimeMillis()
        return dataFormat.format(currentTime)
    }
    /**
     * 获取信息  软件版本 手机版本 手机型号等
     */
    private fun obtainSimpleInfo(context: Context) : HashMap<String, String>{
        var map = HashMap<String, String>()
        var mPackageManager = context.packageManager
        var mPackageInfo : PackageInfo? = null
        try {
            mPackageInfo = mPackageManager.getPackageInfo(context.packageName, GET_ACTIVITIES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        map["versionName"] = mPackageInfo?.versionName ?: "未知"
        map["versionCode"] = "" + (mPackageInfo?.versionCode ?: 0)
        map["MODEL"] = "" + Build.MODEL
        map["SDK_INT"] = "" + SDK_INT
        map["PRODUCT"] = "" + PRODUCT
        map["MOBLE_INFO"] = getMobileInfo()
        return map
    }


    /**
     * 获取手机信息  HomiNote 6.0
     *
     * @return
     */
    private fun getMobileInfo(): String {
        val sb = StringBuffer()
        try {
            // 利用反射获取 Build 的所有属性
            val fields = Build::class.java.declaredFields
            for (field in fields) {
                field.isAccessible = true
                val name = field.name
                val value = field.get(null).toString()
                sb.append("$name=$value")
                sb.append("\n")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return sb.toString()
    }


    /**
     * 获取系统未捕捉的错误信息
     *
     * @param throwable
     * @return
     */
    private fun obtainExceptionInfo(throwable: Throwable): String {
        // Java基础 异常
        val stringWriter = StringWriter()
        val printWriter = PrintWriter(stringWriter)
        throwable.printStackTrace(printWriter)
        printWriter.close()
        return stringWriter.toString()
    }


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful. If a
     * deletion fails, the method stops attempting to delete and returns
     * "false".
     */
    private fun deleteDir(dir: File): Boolean {
        if (dir.isDirectory) {
            val children = dir.listFiles()
            // 递归删除目录中的子目录下
            for (child in children!!) {
                child.delete()
            }
        }
        // 目录此时为空，可以删除
        return true
    }
}