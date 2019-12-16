package com.chen.baselibrary.fix


import android.content.Context
import android.util.Log
import dalvik.system.BaseDexClassLoader
import java.io.*
import java.lang.Exception
import java.lang.reflect.Array
import java.nio.channels.FileChannel

class FixDexManager(var context: Context) {
     private val TAG = this.javaClass.simpleName
    private var mDexDir: File? = null
    init {
        //1.获取应用可以访问的dex目录
        this.mDexDir = context.getDir("odex",Context.MODE_PRIVATE)
    }

    /**
     * 修复dex包
     */
     fun fixDex(fixDexPath : String){
         //2.获取下载好的补丁的dexElement
         //2.1 移动到系统能够访问的 dex目录下 ClassLoader
         var srcFile = File(fixDexPath)
         if (!srcFile.exists()){
             throw FileNotFoundException(fixDexPath)
         }
         var destFile = File(mDexDir, srcFile.name)

         if (destFile.exists()){
             Log.d(TAG, "patch [$fixDexPath] has be loaded.")
             return
         }

         copyFile(srcFile, destFile)

         //2.2ClassLoader读取fixDex路径  为什么加入到集合  已启动可能就要修复 BaseApplication
         var fixDexFiles = ArrayList<File>()
         fixDexFiles.add(destFile)

         fixDexFiles(fixDexFiles)
     }

    @Throws(Exception::class)
    fun loadFixDex(){
        var dexFiles = mDexDir?.listFiles()
        var fixDexFiles = ArrayList<File>()

        if (dexFiles != null){
            for (dexFile in dexFiles) {
                if (dexFile.name.endsWith(".dex")) {
                    fixDexFiles.add(dexFile)
                }
            }
        }
        fixDexFiles(fixDexFiles)
    }

    /**
     * 修复dex
     */
    @Throws(Exception::class)
    fun fixDexFiles(fixDexFiles : ArrayList<File>){
        //1. 先获取已经运行的dexElement
        var applicationClassLoader = context.classLoader
        var applicationDexElements = getDexElementsByClassLoader(applicationClassLoader)
        var optimizedDirectory = File(mDexDir, "odex")
        if (!optimizedDirectory.exists()){
            optimizedDirectory.mkdirs()
        }
        //修复
        for (fixDexFile in fixDexFiles){
            // dexPath dex路径
            //optimizedDirectory 解压路径
            //libraryPath .so 文件位置
            //parent  父ClassLoader
            var fixDexClassLoader = BaseDexClassLoader(
                    fixDexFile.absolutePath, // dex路径 必须要在应用目录下的odex文件中
                    optimizedDirectory,      //解压路径
                    null,  // .so文件位置
                    applicationClassLoader  // 父ClassLoader
            )

            var fixDexElements = getDexElementsByClassLoader(fixDexClassLoader)

            //3. 把补丁的dexElement 插到已经运行的 dexElement的最前面  合并
            //applicationClassLoader 数组  合并 fixDexElements 数组

            //3.1 合并完成
            applicationDexElements = combineArray(fixDexElements, applicationDexElements)
        }
        //3.2 把合并的数组注入到原来的类中  applicationClassLoader
        injectDexElements(applicationClassLoader, applicationDexElements)
    }



    @Throws(IOException::class)
    fun copyFile(src: File, dest: File) {
        var inChannel: FileChannel? = null
        var outChannel: FileChannel? = null
        try {
            if (!dest.exists()) {
                dest.createNewFile()
            }
            inChannel = FileInputStream(src).channel
            outChannel = FileOutputStream(dest).channel
            inChannel!!.transferTo(0, inChannel.size(), outChannel)
        } finally {
            inChannel?.close()
            outChannel?.close()
        }
    }

    /**
     * 从classLoader 中获取dexElements
     */
    @Throws(Exception::class)
    private fun getDexElementsByClassLoader(classLoader : ClassLoader): Any? {
        //1. 先获取pathList
        var pathListField = BaseDexClassLoader::class.java.getDeclaredField("pathList")
        // IOC 熟悉反射
        pathListField.isAccessible = true
        var pathList = pathListField.get(classLoader)

        // 2. pathList 里面的dexElements
        var dexElementsField = pathList.javaClass.getDeclaredField("dexElements")
        dexElementsField.isAccessible = true

        return dexElementsField.get(pathList)
    }

    /**
     * 合并两个数组
     */
    private fun combineArray(arrayLhs : Any?, arrayRhs : Any?): Any {
        var localClass = arrayLhs?.javaClass?.componentType
        var i = Array.getLength(arrayLhs)
        var j = i + Array.getLength(arrayRhs)
        var result = Array.newInstance(localClass, j)
        for (k in 0 until j) {
            if (k < i){
                Array.set(result, k, Array.get(arrayLhs, k))
            }else {
                Array.set(result, k, Array.get(arrayRhs, k - i))
            }
        }
        return result
    }

    /**
     * 把dexElements 注入到classLoader 中
     */
    private fun injectDexElements(classLoader: ClassLoader, dexElements : Any?){
        //先获取 pathList
        var pathListField = BaseDexClassLoader::class.java.getDeclaredField("pathList")
        //IOC 熟悉反射
        pathListField.isAccessible = true
        var pathList = pathListField.get(classLoader)

        //2. pathList里面的dexElements
        var dexelementsField = pathList.javaClass.getDeclaredField("dexElements")
        dexelementsField.isAccessible = true

        dexelementsField.set(pathList, dexElements)
    }

}