package com.chen.fakevibrato.ui.my.view

import android.os.Bundle
import android.view.View
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseFragment
import com.chen.fakevibrato.ui.home.presenter.MainPresenter

/**
 * @author Created by CHEN on 2019/8/29
 * @email 188669@163.com
 * 发现好友
 */
class FindFriendFragment :BaseFragment<MainPresenter>() {
    override fun setView(): Int {
        return R.layout.fragment_find_friend
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView(view: View?) {

    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun Load() {

    }
}