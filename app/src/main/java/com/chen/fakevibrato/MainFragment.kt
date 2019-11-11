package com.chen.fakevibrato

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.chen.fakevibrato.base.BaseFragment
import com.chen.fakevibrato.interfaces.OnDispatchSwipeListener
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.ui.home.view.HomeFragment
import com.chen.fakevibrato.ui.message.view.MessageFragment
import com.chen.fakevibrato.ui.my.view.UserFragment
import com.chen.fakevibrato.ui.samecity.view.SameCityFragment
import com.chen.fakevibrato.utils.DisplayUtils
import com.chen.fakevibrato.utils.MyLog
import com.chen.fakevibrato.widget.MyStatePagerAdapter
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

/**
 * home
 */
class MainFragment(var onDispatchSwipeListener: OnDispatchSwipeListener) : BaseFragment<MainPresenter>() {
    private var adapter: MyStatePagerAdapter? = null
    private val mFragments = ArrayList<Fragment>()
    internal var mTitles = arrayOf("首页", "同城", "", "消息", "我")
    private var sameCityFragment: SameCityFragment? = null
    private var fragment: Fragment? = null
    private var messageFragment: MessageFragment? = null
    private var userFragment: UserFragment? = null


    override fun setView(): Int {
        return R.layout.fragment_main
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView(view: View?) {
        sameCityFragment = SameCityFragment()
        fragment = Fragment()
        messageFragment = MessageFragment()
        userFragment = UserFragment()
        mFragments.add(HomeFragment())
        adapter = MyStatePagerAdapter(childFragmentManager, mFragments)
        viewPager.adapter = adapter
        viewPager.setSwipeable(false)

        val mTabEntities = ArrayList<CustomTabEntity>()
        for (i in mTitles.indices) {
            val finalI = i
            mTabEntities.add(object : CustomTabEntity {
                override fun getTabTitle(): String {
                    return mTitles[finalI]
                }

                override fun getTabSelectedIcon(): Int {
                    return 0
                }

                override fun getTabUnselectedIcon(): Int {
                    return 0
                }
            })
        }
        mTabLayout.setTabData(mTabEntities)
        initListener()
    }


    private fun initListener() {
        val textView = mTabLayout.getTitleView(0)
        val text = textView.text.toString().trim { it <= ' ' }
        val textPaint = textView.paint
        val textPaintWidth = textPaint.measureText(text).toInt()
        mTabLayout.indicatorWidth = DisplayUtils.px2dp(activity, textPaintWidth.toFloat()).toFloat()
        mTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                MyLog.d("viewPager: $position   $viewPager")
//                viewPager.currentItem = position
                val textView = mTabLayout.getTitleView(position)
                val text = textView.text.toString().trim { it <= ' ' }
                val textPaint = textView.paint
                val textPaintWidth = textPaint.measureText(text).toInt()
                mTabLayout.indicatorWidth = DisplayUtils.px2dp(activity, textPaintWidth.toFloat()).toFloat()
                onDispatchSwipeListener.isDispatchSwipe(position != 4)
                if (position == 0) {
                    MyLog.e("mFragments  : " + mFragments.size)
                    mFragments.removeAt(4)
                    mFragments.removeAt(3)
                    mFragments.removeAt(2)
                    mFragments.removeAt(1)
                    adapter?.notifyDataSetChanged()
                } else {
                    if (mFragments.size == 1) {
                        if (sameCityFragment == null) {
                            sameCityFragment = SameCityFragment()
                            sameCityFragment?.let { mFragments.add(it) }
                        } else {
                            sameCityFragment?.let { mFragments.add(it) }
                        }

                        if (fragment == null) {
                            fragment = Fragment()
                            fragment?.let { mFragments.add(it) }
                        } else {
                            fragment?.let { mFragments.add(it) }
                        }

                        if (messageFragment == null) {
                            messageFragment = MessageFragment()
                            messageFragment?.let { mFragments.add(it) }
                        } else {
                            messageFragment?.let { mFragments.add(it) }
                        }

                        if (userFragment == null) {
                            userFragment = UserFragment()
                            userFragment?.let { mFragments.add(it) }
                        } else {
                            userFragment?.let { mFragments.add(it) }
                        }


                        adapter?.notifyDataSetChanged()
                    }
                }

                viewPager.currentItem = position
            }

            override fun onTabReselect(position: Int) {
            }
        })

        ivBottom.setOnClickListener {
            activity?.startActivity(Intent(activity, SwipeActivity::class.java))
        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun Load() {

    }
}