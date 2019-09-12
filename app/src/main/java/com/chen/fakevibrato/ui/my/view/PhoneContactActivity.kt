package com.chen.fakevibrato.ui.my.view

import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseActivity
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_phone_contact.*

/**
 * 手机通讯录
 */
class PhoneContactActivity : BaseActivity<MainPresenter>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_phone_contact
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView() {
        initToolbar(toolbar)
    }

    override fun initListener() {

    }

    override fun initData() {

    }
}