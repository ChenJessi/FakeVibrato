package com.chen.fakevibrato.ui.my.view

import com.chen.fakevibrato.MainActivity
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseActivity
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.ui.my.presenter.EditNormalPresenter
import kotlinx.android.synthetic.main.activity_add_school.*

/**
 * @author Created by CHEN on 2019/8/24
 * @email 188669@163.com
 */
class AddSchoolActivity : BaseActivity<MainPresenter>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_add_school
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