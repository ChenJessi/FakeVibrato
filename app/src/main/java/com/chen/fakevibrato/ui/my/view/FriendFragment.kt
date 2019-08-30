package com.chen.fakevibrato.ui.my.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseFragment
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.ui.my.adapter.FriendAdapter
import kotlinx.android.synthetic.main.fragment_friend.*

/**
 * @author Created by CHEN on 2019/8/29
 * @email 188669@163.com
 * 好友
 */
class FriendFragment :BaseFragment<MainPresenter>() {
    var adapter : FriendAdapter? = null

    var mList = ArrayList<String>()
    override fun setView(): Int {
        return R.layout.fragment_friend
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView(view: View?) {
        adapter = activity?.let { FriendAdapter(it, mList) }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun Load() {

    }
}