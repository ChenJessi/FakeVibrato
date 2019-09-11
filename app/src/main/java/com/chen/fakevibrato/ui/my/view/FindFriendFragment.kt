package com.chen.fakevibrato.ui.my.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseFragment
import com.chen.fakevibrato.bean.ButtonBean
import com.chen.fakevibrato.bean.SwipeBean
import com.chen.fakevibrato.bean.TitleBean
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.ui.my.adapter.FindFriendAdapter
import com.chen.fakevibrato.utils.MyLog
import com.chen.fakevibrato.widget.decoration.TitleItemDecoration
import kotlinx.android.synthetic.main.fragment_find_friend.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author Created by CHEN on 2019/8/29
 * @email 188669@163.com
 * 发现好友
 */
class FindFriendFragment :BaseFragment<MainPresenter>() {
    var adapter : FindFriendAdapter? = null
    var mList = ArrayList<TitleBean>()
    lateinit var titleItemDecoration : TitleItemDecoration
    override fun setView(): Int {
        return R.layout.fragment_find_friend
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView(view: View?) {
        var bean = TitleBean()
        bean.initial = "new";
        var bean1 = TitleBean()
        bean1.initial = "all";
        mList.add(bean)
        mList.add(bean)
        mList.add(bean)
        mList.add(bean1)
        mList.add(bean1)
        mList.add(bean1)
        mList.add(bean1)
        mList.add(bean1)
        mList.add(bean1)
        mList.add(bean1)
        adapter = activity?.let { FindFriendAdapter(it, mList) }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        titleItemDecoration = TitleItemDecoration(activity, mList)
        recyclerView.addItemDecoration(titleItemDecoration)

        initListener()
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun Load() {

    }

    fun initListener(){
        adapter?.onItemClickListener = object : FindFriendAdapter.OnItemClickListener{
            override fun onAttention(position: Int) {

            }

            override fun onClose(position: Int) {
                mList.removeAt(position)
                adapter?.notifyDataSetChanged()

            }

            override fun onItemClick(position: Int) {
                edittext.clearFocus()
            }

        }

        edittext.onFocusChangeListener = View.OnFocusChangeListener {
            _, hasFocus -> MyLog.d("hasFocus  : $hasFocus")
//            edittext.isFocused
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun buttonBean(buttonBean: ButtonBean){
        MyLog.d("ButtonBean : $buttonBean")
    }
}