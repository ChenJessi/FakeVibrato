package com.chen.functionmanager

abstract class FunctionNoParamHasResult<T>(functionName: String) : Function(functionName) {
    abstract fun function(): T
}