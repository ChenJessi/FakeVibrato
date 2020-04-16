package com.chen.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BasePresenter<in BaseView>> : Fragment() ,BaseView {

    private var isFirstLoad = true
    protected  var mPresenter: P? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = if (setView() == 0) null else inflater.inflate(setView(), container, false)
        mPresenter = initPresenter()
        mPresenter?.attachView(this)
        initView()
        initData()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onResume() {
        super.onResume()
        if (isFirstLoad){
            onFirstVisible()
            isFirstLoad = false
        }
        Load()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.isFirstLoad = true
        this.mPresenter = null
    }

    protected abstract fun setView() : Int

    protected abstract fun initPresenter(): P?

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun Load()

    protected abstract fun onFirstVisible()
}