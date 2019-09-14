package com.chen.fakevibrato.ui.home.view

import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseActivity
import com.chen.fakevibrato.ui.home.presenter.MainPresenter

/**
 * @author Created by CHEN on 2019/9/14
 * @email 188669@163.com
 * 搜索
 */
class SearchActivity : BaseActivity<MainPresenter>(){
    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initData() {
       
    }
}