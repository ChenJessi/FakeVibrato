package com.chen.baselibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BasePresenter<BaseView>> : AppCompatActivity(), BaseView {

    protected val TAG = this.javaClass.simpleName
    protected var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getLayoutId() != 0) {
            setContentView(getLayoutId())
        }
        mPresenter = initPresenter()
        if (mPresenter != null) {
            mPresenter?.attachView(this)
        }

        initView()
        initListener()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.mPresenter = null
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initPresenter(): P

    protected abstract fun initView()

    protected abstract fun initListener()

    protected abstract fun initData()

}