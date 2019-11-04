package com.chen.functionmanager

abstract class FunctionHasParamHasResult<T , P>(functionName: String) : Function(functionName) {
    abstract fun function(p : P) : T
}