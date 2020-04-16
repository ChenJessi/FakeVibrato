package com.chen.fakevibrato.base

import com.chen.baselibrary.base.BaseFragment
import com.chen.baselibrary.base.BasePresenter
import com.chen.baselibrary.base.DBasePresenter
import com.chen.baselibrary.base.BaseView

abstract class BaseSupportFragment<P : BasePresenter<in BaseView>> : BaseFragment<P>(), BaseView {

}