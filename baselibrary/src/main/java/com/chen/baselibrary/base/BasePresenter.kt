package com.chen.baselibrary.base


abstract class BasePresenter<V: BaseView> {
    protected val TAG = this.javaClass.simpleName

    protected var mView: V? = null
    fun  attachView(view: V) {
        this.mView = view
    }
}