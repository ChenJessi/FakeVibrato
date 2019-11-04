package com.chen.functionmanager



class FunctionManager {
    private var mNoParamNoResultMap: MutableMap<String, FunctionNoParamNoResult>? = null
    private var mHasParamNoResultMap: MutableMap<String, FunctionHasParamNoResult<*>>? = null
    private var mNoParamHasResultMap: MutableMap<String, FunctionNoParamHasResult<*>>? = null
    private var mHasParamHasResultMap: MutableMap<String, FunctionHasParamHasResult<*, *>>? = null

    private constructor() {
        mNoParamNoResultMap = HashMap()
        mHasParamNoResultMap = HashMap()
        mNoParamHasResultMap = HashMap()
        mHasParamHasResultMap = HashMap()

    }

    companion object {
//        lateinit var instance : FunctionManager<Any>
         val instance: FunctionManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            FunctionManager()
        }


    }
    fun addFunction(function: FunctionNoParamNoResult) {
        mNoParamNoResultMap?.set(function.functionName, function)
    }

    fun  <P> addFunction(function: FunctionHasParamNoResult<P>) {
        mHasParamNoResultMap?.set(function.functionName, function)
    }

    fun <T> addFunction(function: FunctionNoParamHasResult<T>) {
        mNoParamHasResultMap?.set(function.functionName, function)
    }

    fun <T, P> addFunction(function: FunctionHasParamHasResult<T, P>) {
        mHasParamHasResultMap?.set(function.functionName, function)

    }

    fun invokeFunction(functionName: String) {
        var f = mNoParamNoResultMap?.get(functionName)
        if (f != null) {
            f.function()
        } else {
            throw NullPointerException("can't find the method : $functionName")
        }

    }

     fun  <P> invokeFunction(functionName: String, p: P) {
        var f  = mHasParamNoResultMap?.get(functionName)
        if (f != null) {
            (f as FunctionHasParamNoResult<P>).function(p)
        } else {
            throw NullPointerException("can't find the method : $functionName")
        }
    }

    fun <T> invokeFunction(functionName: String, t: Class<T>): T? {
        var f = mNoParamHasResultMap?.get(functionName)
        if (f != null) {
            if (t != null) {
                return t.cast(f.function())
            }
        } else {
            throw NullPointerException("can't find the method : $functionName")
        }
        return null
    }

    fun <T, P> invokeFunction(functionName: String, p: P, t: Class<T>): T? {
        var f  = mHasParamHasResultMap?.get(functionName)
        if (f != null) {
            if (t != null) {
                return t.cast((f as FunctionHasParamHasResult<T, P>).function(p))
            }
        } else {
            throw NullPointerException("can't find the method :  $functionName")
        }
        return null
    }

    fun remove(vararg str: String) {
        if (str.isEmpty()){
            mNoParamNoResultMap?.clear()
            mNoParamHasResultMap?.clear()
            mHasParamNoResultMap?.clear()
            mHasParamHasResultMap?.clear()
            return
        }
        for (i in 0 until str.size) {
            mNoParamNoResultMap?.remove(str[i])
            mNoParamHasResultMap?.remove(str[i])
            mHasParamNoResultMap?.remove(str[i])
            mHasParamHasResultMap?.remove(str[i])
        }

    }
}


