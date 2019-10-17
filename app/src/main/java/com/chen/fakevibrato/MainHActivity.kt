package com.chen.fakevibrato


import android.view.MotionEvent
import android.view.View
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
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainHActivity : BaseActivity<MainPresenter>() {
    private var adapter: MyPagerAdapter? = null
    private val mFragments = ArrayList<Fragment>()
    private var childPosition = 0
    private var onDispatchSwipeListener : OnDispatchSwipeListener? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_main_h
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView() {
        mFragments.add(SwipeFragment())
        mFragments.add(MainFragment())
        mFragments.add(SwipeFragment())

        adapter = MyPagerAdapter(supportFragmentManager, mFragments)
        viewPager.adapter = adapter
        viewPager.setSwipeable(true)
        viewPager.currentItem = 1

    }
//    private var startX: Float = 0.toFloat()
//    private var lastX: Float = 0.toFloat()
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//
////        super.onTouchEvent(event)
//        if (event != null) {
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    MyLog.e("onTouchEvent  down  :"+ event.x)
//                    startX = event.x
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    lastX = event.x
//                    MyLog.e("onTouchEvent  move : $lastX    $startX")
//                    if (childPosition == 1){
//                        this.onDispatchSwipeListener?.isDispatchSwipe(lastX > startX )
//                        return true
//                    }
//
//                }
//                MotionEvent.ACTION_UP -> {
//                }
//            }
//        }
//        return super.onTouchEvent(event)
//    }

    override fun initListener() {

    }

    override fun initData() {

    }

  public fun setOnDispatchSwipeListener(onDispatchSwipeListener : OnDispatchSwipeListener){
      this.onDispatchSwipeListener = onDispatchSwipeListener
  }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun swipeStatus(swipeBean: SwipeBean) {
        childPosition = swipeBean.position
    }
}
