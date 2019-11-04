package com.chen.functionmanager

abstract class  FunctionHasParamNoResult<P>(functionName: String) : Function(functionName) {
    abstract fun function(p: P)
}