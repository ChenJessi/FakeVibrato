package com.chen.fakevibrato


import android.view.MotionEvent
import android.view.View
import androidx.customview.widget.ViewDragHelper
import androidx.fragment.app.Fragment
import com.chen.fakevibrato.base.BaseActivity
import com.chen.fakevibrato.bean.SwipeBean
import com.chen.fakevibrato.interfaces.OnDispatchSwipeListener

import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.utils.MyLog
import com.qmuiteam.qmui.widget.QMUIViewPager
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main_h.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainHActivity : BaseActivity<MainPresenter>(), OnDispatchSwipeListener {
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
