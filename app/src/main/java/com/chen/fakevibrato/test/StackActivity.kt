package com.chen.fakevibrato.test

import com.chen.baselibrary.base.BasePresenter
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseSupportActivity
import kotlinx.android.synthetic.main.activity_stack.*

/**
 * stackView 测试
 */
class StackActivity : BaseSupportActivity<BasePresenter<*>>() {
    var mList = ArrayList<String>()
    override fun getLayoutId(): Int {
        return R.layout.activity_stack
    }

    override fun initPresenter(): BasePresenter<*>? {
        return null
    }

    override fun initView() {
        mList.add("1")
        mList.add("2")
        mList.add("3")
        mList.add("5")
        mList.add("6")
        mList.add("7")
        mList.add("9")
        mList.add("10")
        mList.add("11")
        mList.add("12")
        mList.add("13")
        mList.add("14")
        stackView.adapter = StackAdapter(this@StackActivity,mList)

    }

    override fun initListener() {
        button.setOnClickListener {
            stackView.showNext()
        }
        button1.setOnClickListener {
            stackView.showPrevious()
        }

    }

    override fun initData() {

    }
}