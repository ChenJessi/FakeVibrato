package com.chen.fakevibrato

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.chen.fakevibrato.base.BaseFragment
import com.chen.fakevibrato.interfaces.OnDispatchSwipeListener
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.ui.home.view.HomeFragment
import com.chen.fakevibrato.ui.message.view.MessageFragment
import com.chen.fakevibrato.ui.my.view.UserFragment
import com.chen.fakevibrato.ui.samecity.view.SameCityFragment
import com.chen.fakevibrato.widget.MyStatePagerAdapter
import java.util.ArrayList
import kotlinx.android.synthetic.main.fragment_main.*
/**
 * home
 */
class MainFragment : BaseFragment<MainPresenter>() {
    private var adapter: MyStatePagerAdapter? = null

    override fun setView(): Int {
        return R.layout.fragment_main
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView(view: View?) {
        val mFragments = ArrayList<Fragment>()
        mFragments.add(HomeFragment())
        mFragments.add(SameCityFragment())
        mFragments.add(Fragment())
        mFragments.add(MessageFragment())
        mFragments.add(UserFragment())
        adapter = MyStatePagerAdapter(childFragmentManager, mFragments)
        viewPager.adapter = adapter
        viewPager.setSwipeable(false)
//        adapter?.notifyDataSetChanged()
        initListener()
    }

    private fun initListener(){
//        (activity as MainHActivity).setOnDispatchSwipeListener(object : OnDispatchSwipeListener{
//            override fun isDispatchSwipe(dispatchSwipe: Boolean) {
//                viewPager.setDispatchSwipe(!dispatchSwipe)
//            }
//        })
    }
    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun Load() {

    }
}