package com.chen.fakevibrato


import android.view.MotionEvent
import androidx.fragment.app.Fragment
import com.chen.baselibrary.base.BasePresenter
import com.chen.fakevibrato.base.BaseSupportActivity
import com.chen.fakevibrato.interfaces.OnDispatchSwipeListener

import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main_h.*
import java.util.ArrayList
import org.greenrobot.eventbus.EventBus


class MainHActivity : BaseSupportActivity<MainPresenter>(), OnDispatchSwipeListener {


    override fun isDispatchSwipe(dispatchSwipe: Boolean) {
        viewPager.setSwipeable(dispatchSwipe)
    }

    private var adapter: MyPagerAdapter? = null
    private val mFragments = ArrayList<Fragment>()

    override fun getLayoutId(): Int {
        return R.layout.activity_main_h
    }
    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView() {
        mFragments.add(SwipeFragment())
        mFragments.add(MainFragment(this))
        mFragments.add(SwipeFragment())

        adapter = MyPagerAdapter(supportFragmentManager, mFragments)
        viewPager.adapter = adapter
        viewPager.setSwipeable(true)
        viewPager.currentItem = 1


    }


    override fun initListener() {

    }

    override fun initData() {

    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            EventBus.getDefault().post(ev)
        }
        return super.dispatchTouchEvent(ev)
    }

}
